import GLOOP.*;

/**
 * Fabian Stens
 * 2020-11-19:18.55
 */

public class Landeplatz {
    //Kamera, Umgebung und Tastatur deklarieren
    private GLSchwenkkamera kamera;
    private GLLicht licht;
    private GLBoden boden;
    private GLHimmel himmel;
    private GLTastatur tastatur;
    //GLKugel Array deklarieren
    private GLKugel [] lichter;
    Landeplatz(){
        //Tastatur initialisieren/instanziieren
        tastatur = new GLTastatur();
        erschaffeUmgebung();
        platz();
        lampen();
        keyboard();
    }
    public void erschaffeUmgebung(){
        //Kamera und Umgebung erstellen
        kamera = new GLSchwenkkamera();
        kamera.setzePosition(0,300,500);
        licht  = new GLLicht();
        boden  = new GLBoden("textures/Gras.jpg");
        himmel = new GLHimmel("textures/Himmel.jpg");
    }
    public void platz(){
        //Landeplatz erstellen
        GLZylinder feld = new GLZylinder(0,0,0,1000,1);
        feld.drehe(90,0,0);
        feld.setzeTextur("textures/Feld.jpg");
        feld.verschiebe(0,0,0);
    }
    public void lampen(){
        //Lampen mit dem Array Lichter erstellen und im Kreis positionieren,Leuchten lassen(weiß)
        lichter = new GLKugel[20];
        for (int i=0; i < lichter.length; i++){
            lichter[i] = new GLKugel(950,0,0,50);
            lichter[i].setzeSelbstleuchten(0, 0, 0);
            lichter[i].drehe(0, (360/20)*i, 0, 0, 0, 0);
        }
    }
    public void blinke(){
        //Lichter blinken rot (0,1 Sekunde)
        for(int i = 0; i<lichter.length; i++){
           lichter[i].setzeSelbstleuchten(100, 0, 0);
        }
        Sys.warte(100);
        for(int i = 0; i<lichter.length; i++){
           lichter[i].setzeSelbstleuchten(0, 0, 0);
        }
        Sys.warte(100);
    }
    public void starteLauflicht(){
        //Lauflicht(rot, 0,1 Sekunde)
        for(int i = 0; i<lichter.length; i++){
           lichter[i].setzeSelbstleuchten(255, 0, 0);
           Sys.warte(100);
           lichter[i].setzeSelbstleuchten(0,0,0);
        }
    }
    public void keyboard(){
        while(!tastatur.esc()){
            //wenn B gedrückt, blinke
            while(tastatur.istGedrueckt('b')){
                blinke();
            }
            //wenn L gedrückt, starte Laufleuchte
            while(tastatur.istGedrueckt('l')){
                starteLauflicht();
            }
            //Steuerung
            if(tastatur.istGedrueckt('w')||tastatur.oben()){
                kamera.verschiebe(0, 0, -1);
                Sys.warte();
            }
            if(tastatur.istGedrueckt('a')||tastatur.links()){
                kamera.verschiebe(-1, 0, 0);
                Sys.warte();
            }
            if(tastatur.istGedrueckt('s')||tastatur.unten()){
                kamera.verschiebe(0, 0, 1);
                Sys.warte();
            }
            if(tastatur.istGedrueckt('d')||tastatur.rechts()){
                kamera.verschiebe(1, 0, 0);
                Sys.warte();
            }
            if(tastatur.shift()){
                kamera.verschiebe(0, 1, 0);
                Sys.warte();
            }
            if(tastatur.strg()){
                kamera.verschiebe(0, -1, 0);
                Sys.warte();
            }
        }
    }
}
