package com.chamud.cheziandsima.unitcoverter;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;

import java.text.DecimalFormat;

/**
 * Created by CheziAndSima on 08/05/2015.
 */
public class Quantity {

    private Unit from;
    private Unit to;
    private double amount;
    private Context ctx;

    public Quantity(Unit from, Unit to, double amount, Context ctx) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.ctx = ctx;
    }

    public String getCalculateAmount() {
        double calcAmount = amount * ((Unit.tbs.getUnitValue() / from.getUnitValue()) / (Unit.tbs.getUnitValue() / to.getUnitValue()));

        DecimalFormat df = new DecimalFormat("0.00");

        int currentId =  ctx.getResources().getIdentifier(to.name(),"string",
                ctx.getPackageName());

        String unitName = ctx.getResources().getString(currentId);
        return unitName+ " "+ String.valueOf(df.format(calcAmount));
    }


    public static enum Unit {
        tsp(1.0d), tbs(0.3333d), cup(0.0208d), oz(0.1666d),
        pint(0.0104d), quart(0.0052d), gallon(0.0013), pound(0.0125d),
        ml(4.9289d), liter(0.0049d), mg(5687.5d), kg(0.0057d);

        final double byBaseUnit;

        private Unit(double inTsp) {
            this.byBaseUnit = inTsp;
        }

        public double getUnitValue() {
            return this.byBaseUnit;
        }
    }
}

