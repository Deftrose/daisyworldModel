/*
    The Prams Class used to define some const values
    Including the solar-luminosity, albedo of daisies and surfaces
    Other initial properties would also be defined here

 */

public class Params {

    // The max size of the world
    final static int MAX_X = 11;

    final static int MAX_Y = 11;

    // The albedo of Black daisy
    final static double BLACK_ALBEDO = 0.25;

    // The albedo of White daisy
    final static double WHITE_ALBEDO = 0.75;

    // The albedo of surface
    final static double SUR_ALBEDO = 0.4;

    // Solar luminosity
    final static double SOLAR_LUMINOSITY = 1;

    // The max age of daisy
    final static int AGE_MAX = 25;

    // The proportion of initial white daisy, shouldn't over 0.5
    final static double WHITE_DAISY = 0.5;

    // The proportion of initial black daisy, shouldn't over 0.5
    final static double BLACK_DAISY = 0.5;

    // Real luminosity
    final double REAL_LUMINOSITY = 0;

    // Initial patch temperature
    final static double INIT_TEMP = 0.0;

    // Diffuse parameter
    final static double DIFUSE_PARAM = 0.5;

}
