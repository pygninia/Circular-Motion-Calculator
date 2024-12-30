/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsrnas;

/**
 *
 * @author Billey
 */
public class CircleClass {
    private double radius;

    public CircleClass(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double calculateTangentialVelocity(double angularVelocity) {
        return angularVelocity * radius;
    }

    public double calculateCentripetalAcceleration(double speed) {
        return speed * speed / radius;
    }

    public double calculateCentripetalForce(double mass, double speed) {
        return mass * calculateCentripetalAcceleration(speed);
    }
}