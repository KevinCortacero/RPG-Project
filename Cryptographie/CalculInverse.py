'''
Created on 16 f�vr. 2016

@author: MSI
'''


def Fermat(x,p):
    print("Calcul de l'inverse de {0} par Fermat, dans Z/{1}".format(x, p))
    print("On cherche donc x^(p-2) = {0}^({1})".format(x,p-2))
    mod = p-2
    exposant = 0
    inverse = 0
    while(mod != 0) :
        print (mod % 2)
        if (mod % 2 == 1):
            inverse = (inverse + x**(2**exposant)) % p-2
        mod = int(mod/2)
        exposant +=1
    print("{0}^-1 = {1} dans Z/{2}".format(x,inverse,p))  
    print(x**(p-2) % p)
    
if __name__ == '__main__':
    Fermat(7, 13)


