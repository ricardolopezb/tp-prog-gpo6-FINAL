import json
import urllib.error, urllib.parse, urllib.request
import os
x = dict()
print()
javaZoneFile = open(os.getcwd()+'\\src\\TP\\archivos\\Zona.txt', 'r')
if javaZoneFile.mode == 'r':
    zonaBuscada = javaZoneFile.read()
addressInput = zonaBuscada.rstrip() + ', Argentina'

#print(addressInput)

x['address'] = addressInput
x['key'] = 42
encodedAddressInput = urllib.parse.urlencode(x)
fullUrl = 'http://py4e-data.dr-chuck.net/json?'+ encodedAddressInput
urlHandle = urllib.request.urlopen(fullUrl).read().decode()
locationJson = json.loads(urlHandle)
y = locationJson['results'][0]["geometry"]['location']

f = open(os.getcwd()+'\\src\\TP\\archivos\\Coordenadas.txt', 'w')

lat = y['lat']
lng = y['lng']

f.write(str(lat)+","+str(lng))
#print(str(lat)+","+str(lng))

print('listo')

# Hacer metodo en Java que triggeree este programa
# Hacer que este programa cree un archivo donde escriba la var y, long y lat
# Que el metodo de Java lea ese file, cree el objeto Location, y luego elimine el archivo
# Devuelve Location.



#placeID = locationJson["results"][0]["place_id"]
#print(placeID)