PRIX_BASE = 3.8

PRIX_SALADE = 0.2

PRIX_TOMATES = 0.2

PRIX_OIGNONS = 0.3

PRIX_FRITES = 0.5


class Kebab:

    def __init__(self, sauce):

        self.sauce = sauce

        self.base = PRIX_BASE

        self.salade = PRIX_SALADE

        self.tomates = PRIX_TOMATES

        self.oignons = PRIX_OIGNONS

        self.frites = PRIX_FRITES


    @property

    def prix(self):

        return (self.base + self.salade + self.tomates + self.oignons +

                self.frites)


    def __str__(self):

        return 'Kebab'


    def __repr__(self):

        s = 'Kebab ({0}) ;'.format(self.prix)

        for it in self.__dict__.items():

            s +=' {0} ({1}) '.format(*it)

        return s
    
if __name__ =='__main__' :
    k = Kebab("samourai")
    print(k.__repr__())
    