/**
 * Created by workmode on 9/28/16.
 */

import processing.core.PApplet;
import processing.core.PVector;

public class Mover {

    private PApplet parent;
    private PVector location, velocity, acceleration;
    private float topSpeed = 10;

    public Mover(PVector location, PVector velocity, PVector acceleration, PApplet parent){
        this.location = location;
        this.velocity = velocity;
        this.parent = parent;
        this.acceleration = acceleration;
    }

    /*
    update mover's location by adding velocity
     */
    public void update(){

        PVector mouse = new PVector(parent.mouseX, parent.mouseY);
        PVector dir = PVector.sub(mouse, location);

        float distance = dir.mag();

        dir.normalize();
        dir.mult(distance/100);
        acceleration = dir;

        velocity.add(acceleration);
        velocity.limit(topSpeed);
        location.add(velocity);
    }

    /*
    draw ellipse to mover's location
     */
    public void display(){
        parent.noStroke();
        parent.fill(175);
        parent.ellipse(location.x, location.y, 16, 16);
    }

    public void display3D(){
        parent.pushMatrix();
        parent.stroke(255);
        parent.sphereDetail(4);
        parent.noFill();
        parent.translate(location.x, location.y, location.z);
        parent.sphere(20);
        parent.popMatrix();
    }

    /*
    check to see if mover is passed an edge, and if so, move to opposite edge
     */
    public void checkEdges(){
        if (location.x > parent.width){
            location.x = 0;
        } else if(location.x < 0){
           location.x = parent.width;
        }

        if (location.y > parent.height){
            location.y = 0;
        } else if (location.y < 0){
            location.y = parent.height;
        }
    }

    public PVector getLocation(){
        return location;
    }
//    public void switchAcceleration(){
//        acceleration = PVector.random2D();
//        acceleration.mult(parent.random(2));
//    }


}
