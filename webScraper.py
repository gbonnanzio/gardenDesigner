from bs4 import BeautifulSoup
from urllib.request import Request, urlopen
import requests
import csv
import glob


def getFlowerData(site):
    #hdr = {'User-Agent': 'Mozilla/5.0'}
    #print((site))
    hdr = {'User-Agent': 'Mozilla/5.0'}
    req = Request(site,headers=hdr)
    page = urlopen(req)
    soup = BeautifulSoup(page,'lxml')
    #source = requests.get(site).text
    #soup = BeautifulSoup(source,'lxml')
    #print(soup.prettify())
    div = soup.find('div',id='details')
    lis = div.find_all('li')
    final_data = []
    indexes = [1,3,4,5]
    for i in indexes:
        curr_li = lis[i]
        curr_spans = curr_li.find_all('span')
        data = curr_spans[1].text
        data_list = data.split(',')
        if(len(data_list)>1):
            tmp_data = data_list[0] + " to" + data_list[1]
        else:
            tmp_data = data_list[0]
        final_data.append(tmp_data)
    return final_data

def main():
    # using downloaded html files:
    with open('native_plants.html') as html_file:
        soup = BeautifulSoup(html_file,'lxml')


    # navigating HTML
    #body = soup.find('body')
    #section = soup.find(id='plants-list')
    div = soup.find(id='initial-plants')
    # open csv file and start writing
    with open("plant_data.csv",'w',newline='',encoding='utf-8') as csvFile:
        csvWriter = csv.writer(csvFile)
        csvWriter.writerow(['Name','Scientific Name','Image File Path','Shading','Soil Moisture','Flowering Period','Soil pH'])
        allArticles = div.find_all('article',class_='')
        plantNum = 0
        for article in allArticles:
            print(plantNum)
            plantNum += 1
            plantName = (str(article.find('h1').text))
            # scientific name either in i or p 
            try:
                sciName = article.find('i').getText()
            except (AttributeError):
                sciName = article.find('p').getText()  

            directory = 'native_plants_files/*'
            myList = glob.glob(directory)
            #print(myList)
            sciNameList = sciName.split()
        
            if(len(sciNameList)>1):
                sciNamePic = sciNameList[0] + "_" + sciNameList[1]
            else: 
                sciNamePic = sciNameList[0]

            flower_link =  str(article.find('a',class_='target').get('href'))
            #print(flower_link)
            flower_data = getFlowerData(flower_link)

            for img_name in glob.glob(directory):
                if(sciNamePic in img_name):
                    currRow = [plantName,sciName,img_name,flower_data[0],flower_data[1],flower_data[2],flower_data[3]]
                    csvWriter.writerow(currRow)
                    break
            
main()