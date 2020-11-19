import GLOOP.*;

/**
 * Fabian Stens
 * 2020-11-18:09.45
 */

public class Landeplatz {
    //Kamera und Umgebung deklarieren
    private GLSchwenkkamera kamera;
    private GLLicht licht;
    private GLBoden boden;
    private GLHimmel himmel;
    private GLTastatur tastatur;
    private GLKugel [] lichter;
    Landeplatz(){
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
        GLZylinder feld = new GLZylinder(0,0,0,1000,1);
        feld.drehe(90,0,0);
        feld.setzeTextur("textures/Feld.jpg");
        feld.verschiebe(0,0,0);
    }
    public void lampen(){
        lichter = new GLKugel[20];
        for (int i=0; i < lichter.length; i++){
            lichter[i] = new GLKugel(950,0,0,50);
            lichter[i].setzeSelbstleuchten(0, 0, 0);
            lichter[i].drehe(0, (360/20)*i, 0, 0, 0, 0);
        }
    }
    public void blinke(){
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
        for(int i = 0; i<lichter.length; i++){
           lichter[i].setzeSelbstleuchten(255, 0, 0);
           Sys.warte(100);
           lichter[i].setzeSelbstleuchten(0,0,0);
        }
    }
    public void keyboard(){
        while(!tastatur.esc()){
            while(tastatur.istGedrueckt('b')){
                blinke();
            }
            while(tastatur.istGedrueckt('l')){
                starteLauflicht();
            }
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
