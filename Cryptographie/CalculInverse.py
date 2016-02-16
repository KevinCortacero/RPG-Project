'''
Created on 16 fevr. 2016

@author: MSI
'''


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
        
    print("\n{0}^-1 = {1} dans Z/{2}".format(x,result,modulo))  
    
if __name__ == '__main__':
    exponentiation(12044, 23449, 3)


