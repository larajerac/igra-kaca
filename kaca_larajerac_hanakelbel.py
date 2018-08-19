###IGRA KAČA###

#AVTOR: HANA KELBEL IN LARA JERAČ

import random
import string
import tkinter

GLAVA = 'O' # znak za glavo kače
HRANA = 'o' #znak, ki bo sestavljal telo kače


class Aplikacija:
    ''' Ustvarimo objekt-naša aplikacija. Določimo naslov in velikost.'''
    naslov = 'KAČA'
    velikost = 700, 700

    def __init__(self, master):
        '''ustvarimo spremenljivke.'''
        self.master = master

        self.glava = None
        self.glava_pozicija = None
        self.deli = []
        self.deli_pozicije = []
        self.hrana = None
        self.hrana_pozicija = None
        self.smer = None
        self.premik = True

        self.running = False
        self.init()
        
    def init(self):
        self.master.title(self.naslov)

        self.canvas = tkinter.Canvas(self.master, background="#FFEC8B") #ustvarimo platno
        self.canvas.grid(sticky=tkinter.NSEW)

        self.start_gumb = tkinter.Button(self.master, text='Start', background = 'black', foreground = "white",command=self.on_start) #ustvarimo gumb za začetek
        self.start_gumb.grid(sticky=tkinter.EW)
        
        #določimo tipke za premik:
        self.master.bind('w', self.gor)
        self.master.bind('a', self.levo)
        self.master.bind('s', self.dol)
        self.master.bind('d', self.desno)

  
        self.master.columnconfigure(0, weight=1)
        self.master.rowconfigure(0, weight=1)
        self.master.resizable(False, False)
        self.master.geometry('%dx%d' % self.velikost)
   
    def on_start(self):
        self.reset()
        if self.running:
            self.running = False
            self.start_gumb.configure(text='Start')
        else:
            self.running = True
            self.start_gumb.configure(text='Stop')
            self.start()

    def reset(self):
        #ob ponovnem poizkuse se ponastavi
        self.deli.clear() 
        self.deli_pozicije.clear() 
        self.canvas.delete(tkinter.ALL)

    def start(self):

        dolzina = self.canvas.winfo_width()
        visina = self.canvas.winfo_height()

        self.canvas.create_rectangle(10, 10, dolzina-10, visina-10,dash=(5, 1, 2, 1), width=2) #ustvarimo notranji kvadrat kjer igra poteka
        self.smer = random.choice('wasd')
        glava_pozicija = [round(dolzina // 2, -1), round(visina // 2, -1)] #določimo pozicijo glave ob startu
        self.glava = self.canvas.create_text(tuple(glava_pozicija), text=GLAVA) #izrišemo glavo
        self.glava_pozicija = glava_pozicija
        self.dodaj_hrano()
        self.tick()

    def dodaj_hrano(self):

        dolzina = self.canvas.winfo_width()
        visina = self.canvas.winfo_height()
        positions = [tuple(self.glava_pozicija), self.hrana_pozicija] + self.deli_pozicije

        pozicija = (round(random.randint(20, dolzina-20), -1), round(random.randint(20, visina-20), -1))
        while pozicija in positions:
            pozicija = (round(random.randint(20, dolzina-20), -1), round(random.randint(20, visina-20), -1))

        character = random.choice(HRANA)
        self.hrana = self.canvas.create_text(pozicija, text=character)
        self.hrana_pozicija = pozicija
        self.food_character = character

    def tick(self):
    
        dolzina = self.canvas.winfo_width()
        visina = self.canvas.winfo_height()
        prejsnja_pozicija_glava = tuple(self.glava_pozicija)

        if self.smer == 'w':
            self.glava_pozicija[1] -= 10
        elif self.smer == 'a':
            self.glava_pozicija[0] -= 10
        elif self.smer == 's':
            self.glava_pozicija[1] += 10
        elif self.smer == 'd':
            self.glava_pozicija[0] += 10

        #če se zaletimo v okvir ali v kačo je igre konec
            #preverjamo pozicijo glave
        glava_pozicija = tuple(self.glava_pozicija)
        if (self.glava_pozicija[0] < 10 or self.glava_pozicija[0] >= dolzina-10 or
            self.glava_pozicija[1] < 10 or self.glava_pozicija[1] >= visina-10 or
            any(deli_pozicija == glava_pozicija for deli_pozicija in self.deli_pozicije)):
            self.game_over()
            return

        if glava_pozicija == self.hrana_pozicija:
            self.canvas.coords(self.hrana, prejsnja_pozicija_glava)
            self.deli.append(self.hrana)
            self.deli_pozicije.append(prejsnja_pozicija_glava)
            self.dodaj_hrano()

        if self.deli:
            prejsnja_pozicija = prejsnja_pozicija_glava
            for index, (clen, pozicija) in enumerate(zip(self.deli, self.deli_pozicije)):
                self.canvas.coords(clen, prejsnja_pozicija)
                self.deli_pozicije[index] = prejsnja_pozicija
                prejsnja_pozicija = pozicija

        self.canvas.coords(self.glava, glava_pozicija)
        self.premik = True

        if self.running:
            self.canvas.after(50, self.tick)

    def game_over(self):

        #ob koncu igre izpišemo točke
        dolzina = self.canvas.winfo_width()
        visina = self.canvas.winfo_height()

        self.running = False
        self.start_gumb.configure(text='Start')
        tocke = len(self.deli) * 10
        self.canvas.create_text((round(dolzina // 2, -1), round(visina // 2, -1)), text='Konec igre. Rezultat: %d' % tocke)

    def gor(self, event):
        if self.premik and not self.smer == 's':
            self.smer = 'w'
            self.premik = False

    def dol(self, event):
        if self.premik and not self.smer == 'w':
            self.smer = 's'
            self.premik = False

    def levo(self, event):
        if self.premik and not self.smer == 'd':
            self.smer = 'a'
            self.premik = False

    def desno(self, event):
        if self.premik and not self.smer == 'a':
            self.smer = 'd'
            self.premik = False


def main():
    root = tkinter.Tk()
    Aplikacija(root)
    root.mainloop()


if __name__ == '__main__':
    main()
