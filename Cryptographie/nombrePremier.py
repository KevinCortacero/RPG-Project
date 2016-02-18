'''
Created on 18 fevr. 2016

@author: MSI
'''


def test(l, nombre):
    for el in l :
        if nombre % el == 0 :
            return False
    return True


if __name__ == '__main__' :
    l = list()
    l.append(2)
    nombre = 3
    nombre = int(nombre)
    print("go")
    while l.__len__() < 100000 :
        if test(l, nombre) :
            print(nombre)
            l.append(nombre)
        nombre += 2
    print (l)
   
            

