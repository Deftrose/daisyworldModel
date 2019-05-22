import java.util.Random;

/**
 * This World Class is used to model the daisy world which contains
 * an field of patches. Daisies would be planted on those daisies.
 *
 * The tick method would model how would each state change though a fixed time period
 */
public class World {

    private double globalTemp ;
    private Patch[][] patches ;
    private int white_num;
    private int black_num;

    public World(){
        /*
            Initialise the system state and load the system initial properties
         */
        this.patches = new Patch[Params.MAX_X][Params.MAX_Y];

        init(patches);

        /*
        for(int i=0 ; i<10 ; i++){
            for(int m =0; m<10 ;m++){
                System.out.println(patches[i][m].getDaisy().getDaisyColor());
            }
        }*/
    }

    // Setter for global temperature
    public double getGlobalTemp() {
        return globalTemp;
    }

    // How state transform to another state
    public void tick(){

            // Reset the daisy counter
            white_num = 0;
            black_num = 0;

            // Check all the patches, if the daisy on a patch would alive and reproduce
            checkAlive(patches);

            // Calculate the temperature of each patch
            for(int x=0; x< Params.MAX_X ; x++ ){
                for(int y=0 ; y< Params.MAX_Y ; y++){
                    patches[x][y].calTeperatrue();
                }
            }

            // Calculate the temperature of each patch after diffusion
            for(int x=0; x< Params.MAX_X; x++){
                for(int y=0; y< Params.MAX_Y; y++){
                    diffuse(x,y,patches);
                }
            }

            // Set the global temperature
            globalTemp = setGlobalTemper(patches);

            // Calculate the number of white and black daisy
            for(int x=0; x< Params.MAX_X; x++){
                for(int y=0; y< Params.MAX_Y; y++){
                    if(patches[x][y].getDaisy() != null){
                        switch (patches[x][y].getDaisy().getDaisyColor()){
                            case BLACK :
                                black_num ++; break;
                            case WHITE :
                                white_num ++; break;
                        }
                    }
                }
            }

            //System.out.println(globalTemp);
            System.out.println(white_num);
            //System.out.println(black_num);
        }



    // Use this method to initialise the daisy world
    // All the patches be created and plant the daisies according to the settings
    private static void init(Patch[][] patches){
        Random random = new Random();
        int temp_x,temp_y;
        Patch tempPatch ;

        for(int x=0 ; x< Params.MAX_X ; x++){
                for(int y=0 ; y< Params.MAX_Y ; y++){
                patches[x][y] = new Patch(null, Params.INIT_TEMP);
            }
        }

        // plant the white daisies
        for(int i =0; i < ( (int) (Params.WHITE_DAISY * Params.MAX_X * Params.MAX_Y) ) ; i++ ){
            // only plant daisies where there is no daisy
            do{
                temp_x = random.nextInt(Params.MAX_X);
                temp_y = random.nextInt(Params.MAX_Y);
                tempPatch = patches[temp_x][temp_y];
            }while(tempPatch.getDaisy() != null);
            patches[temp_x][temp_y].setDaisy(new Daisy(Daisy.Color.WHITE,random.nextInt(Params.AGE_MAX)));
        }

        // plant the black daisies
        for(int i =0; i< ( (int)(Params.BLACK_DAISY * Params.MAX_X * Params.MAX_Y) ) ; i++){
            // only plant daisies where there is no daisy
            do{
                temp_x = random.nextInt(Params.MAX_X);
                temp_y = random.nextInt(Params.MAX_Y);
                tempPatch = patches[temp_x][temp_y];
            }while(tempPatch.getDaisy() != null);
            patches[temp_x][temp_y].setDaisy(new Daisy(Daisy.Color.BLACK,random.nextInt(Params.AGE_MAX)));
        }
    }

    // In each tick, each patch would diffuse the heat to its neighbors
    private static void diffuse(int location_x, int location_y, Patch[][] patches ){

        // The patch would gain energy from its neighbors
        for(int x = location_x-1; x<= location_x+1 ; x++){
            for( int y= location_y-1; y<= location_y+1 ; y++){
                // The patches out of border and the patch itself would be excluded
                boolean ex_condition = (x<0 || x >= Params.MAX_X
                        || y<0 || y >= Params.MAX_Y
                        || (x == location_x && y == location_y ));
                if(!ex_condition){
                    // the energy would be absorbed by this one
                    patches[location_x][location_y].setTemperature( patches[location_x][location_y].getTemperature()
                            + patches[x][y].getTemperature() * Params.DIFUSE_PARAM / 8 );
                    // the neighbor would give out 1/16 of its energy
                    patches[x][y].setTemperature( patches[x][y].getTemperature()* (1 - Params.DIFUSE_PARAM / 8) );
                }
            }
        }
    }

    // Set the global temperature according to the average
    private static double setGlobalTemper(Patch[][] patches){
        double tempSum = 0;

        for(int x=0; x< Params.MAX_X ;x++){
            for(int y =0; y< Params.MAX_Y ;y++){
                tempSum +=patches[x][y].getTemperature();
            }
        }
        return tempSum/(Params.MAX_Y * Params.MAX_X);
    }

    // Check if the daisy would live on or die
    private static void checkAlive(Patch[][] patches){
        for(int x =0 ; x< Params.MAX_X ; x++){
            for(int y =0 ; y< Params.MAX_Y ; y++){
                boolean canReproduce = true;
                canReproduce = patches[x][y].checkSurviability();
                if(canReproduce){
                    Daisy newDaisy = new Daisy( patches[x][y].getDaisy().getDaisyColor(),0);
                    if(Neighbor.getRandomEmptyNeighbor(x,y,patches)!= null)
                        Neighbor.getRandomEmptyNeighbor(x,y,patches).setDaisy(newDaisy);
                }
            }
        }
    }
}
