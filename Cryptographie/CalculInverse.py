'''
Created on 16 fevr. 2016

@author: MSI
'''
import math


def exponentiation(x,modulo,exposant):
    
    print("Calcul de l'inverse de {0} par Fermat, dans Z/{1}".format(x, modulo))
    print("On cherche donc x^(p-2) = {0}^({1})".format(x,exposant))
    
    result = 1
    binaire = 0
    valeur = x % modulo
    
    while(exposant != 0) :
        print ("{0}^{1} = {2} mod {3}".format(x, 2**binaire, valeur, modulo))
        if (exposant % 2 == 1):
            result = (result * valeur) % modulo
        exposant = int(exposant/2)
        binaire +=1
        valeur = (valeur**2) % modulo
        
    print("\n{0}^-1 = {1} dans Z/{2}\n\n".format(x,result,modulo))  
    return result
    
def attaqueSimmons(x,modulo,exposant):
    message = x
    x = exponentiation(x, modulo, exposant)
    ordre = 1
    '''print("x = ", x)'''
    save = x
    while (x != message):
        save = x
        x = exponentiation(x, modulo, exposant)
        ordre += 1
    print ("Le message codé est ", save)
    print ("L'ordre est ", ordre)
        
        
def pollard3(n, a1):
    a = a1
    indice = 1
    pgcd = 1
    while pgcd == 1 :
        print ("a{0} = {1}".format(indice, a))
        print ("({0};{1})={2}".format(n,a-1,pgcd))
        indice +=1
        a = a**indice % n
        pgcd = math.gcd(n, a-1)
    print ("a{0} = {1}".format(indice, a))
    print ("({0};{1})={2}".format(n,a-1,pgcd))
    print ("L'inverse est ", int(n/pgcd))
    
def pollard2(n, x0, y0, b):
    x = x0
    y = y0
    indice = 0
    pgcd = 1
    while pgcd == 1 :
        print ("x{0} = {1}".format(indice, x))
        print ("y{0} = {1}".format(indice, y))
        print ("({0};{1})={2}".format(x-y,n,pgcd))
        indice +=1
        x = (x**2 + b) % n
        y = (y**2 + b)**2 % n + b
        pgcd = math.gcd(x-y,n)
    print ("x{0} = {1}".format(indice, x))
    print ("y{0} = {1}".format(indice, y))
    print ("({0};{1})={2}".format(x-y,n,pgcd))
    print ("L'inverse est ", int(n/pgcd))
    
        
if __name__ == '__main__':
    pollard2(81061,65541,65541,13612)
    exponentiation(2, 347, 173)
    

