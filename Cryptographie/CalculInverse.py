'''
Created on 16 f�vr. 2016

@author: MSI
'''


def Fermat(x,p,n):
    print("Calcul de l'inverse de {0} par Fermat, dans Z/{1}".format(x, n))
    print("On cherche donc x^(p-2) = {0}^({1})".format(x,p))
    mod = p
    valeur = x
    compteur = inverse = 1
    while(mod != 0) :
        print (mod % 2)
        print("{0}^{1} = {2} ({3})".format(x,compteur,valeur,n))
        if (mod % 2 == 1):
            inverse *= valeur % (n)
        mod = int(mod/2)
        valeur = (valeur**2) % (n)
        compteur *= 2
    print("{0}^-1 = {1} dans Z/{2}".format(x,inverse % (p),p))  
    print(x**(p) % n)
    
if __name__ == '__main__':
    Fermat(12044, 2, 23449)


