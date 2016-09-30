import processing.core.*;
import processing.core.PVector;
import processing.opengl.*;

public class MainApp extends PApplet {


    Mover[] movers = new Mover[2];

    PVector location, velocity, acceleration;
    Mover mover;

    public static void main(String[] args) {
        PApplet.main("MainApp", args);
    }

    public void settings() {
        fullScreen(P3D);
    }

    public void setup() {
        smooth();
        lights();
        directionalLight(0,255,255,0,1,-1 );
        noCursor();
        location = new PVector(width/2,height/2);
        velocity = new PVector(0,0);
        acceleration = new PVector((float)-.001, (float)0.01);
        mover  = new Mover(location, velocity, acceleration,this);


        for (int i = 0; i < movers.length; i++){
            movers[i] = new Mover(new PVector(random(width), random(height),0), new PVector(0,0), new PVector(0,0), this);
        }

    }

    public void draw() {
        fill(0,0,0,30);
        rect(0,0,width, height);

        mover.update();
        mover.checkEdges();
       // mover.display();


        for (Mover move:movers){
            move.update();
            move.checkEdges();
           // move.display();

            move.display3D();
        }
    }

    public void mousePressed(){
        for (int i = 0; i < movers.length; i++){
            float prevX = movers[i].getLocation().x;
            float prevY = movers[i].getLocation().y;
            
            movers[i] = new Mover(new PVector(prevX + random(width/10), prevY + random(height/10)), velocity, acceleration, this);
        }
    }



}



