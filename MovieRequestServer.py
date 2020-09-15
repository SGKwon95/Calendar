from lxml.html import fromstring, tostring
from flask import Flask, jsonify
from flask_restful import Resource, Api, reqparse
from flask_cors import CORS
import requests, json
import datetime

def scrape_movie(response):
    datas = {}
    root = fromstring(response.content)
    linkList = []
    releaseDateList = []
    nameList = []
    
    for a in root.xpath('//ul[@class="lst_detail_t1"]/li/dl[@class="lst_dsc"]/dt[@class="tit"]/a'):
        linkList.append('https://movie.naver.com' + a.get('href'))
        name = tostring(a,pretty_print=True, encoding='euc-kr').decode('euc-kr')
        start = name.find('>') + 1
        end = name.find('</a>', 1)
        nameList.append(name[start:end])
        
    
    for a in root.xpath('//ul[@class="lst_detail_t1"]/li/dl[@class="lst_dsc"]/dd/dl[@class="info_txt1"]/dd'):
        tmp = tostring(a, pretty_print=True).decode('utf-8')
            
        start = tmp.find('\t' + str(datetime.date.today().year))
        target = tmp[start:start+11]
        
        if target != '':
            if target[5] != '.':
                releaseDateList.append(target[1:5])
            elif target[8] != '.':
                releaseDateList.append(target[1:8])
            else:
                releaseDateList.append(target[1:])
        
    for i in range(len(nameList)):
        if i < len(releaseDateList):
            datas[nameList[i]] = releaseDateList[i] + '|' + linkList[i]
        
    return datas
        
    
if __name__ == "__main__":
    
    app = Flask(__name__)
    
    # data 정렬을 방지한다.
    app.config['JSON_SORT_KEYS'] = False
    
    cors = CORS(app, resources={"/": {"origins": "http://localhost:8080"}})
    api = Api(app)
    
    @app.route('/')
    def PythonServerResponse():
        session = requests.Session()
    
        # 스크래핑 대상 URL
        response = session.get('https://movie.naver.com/movie/running/premovie.nhn')
    
        datas = scrape_movie(response)
        
        for movieName, content in datas.items():
            print(movieName, content)
        return jsonify(datas)
    
    app.run()