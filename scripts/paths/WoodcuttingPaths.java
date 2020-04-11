package scripts.paths;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;

public class WoodcuttingPaths {
    public static final Tile [] EAST_VARROCK_TREE = {
            new Tile(3282, 3429, 0),
            new Tile(3272, 3428, 0),
            new Tile(3269, 3428, 0),
            new Tile(3265, 3428, 0),
            new Tile(3260, 3428, 0),
            new Tile(3258, 3428, 0),
            new Tile(3256, 3427, 0),
            new Tile(3254, 3426, 0),
            new Tile(3254, 3423, 0),
            new Tile(3254, 3419, 0)
    };
    public static final Tile [] LUMBRIDGE_WILLOW_PATH = {
            new Tile(3234, 3240, 0),
            new Tile(3234, 3233, 0),
            new Tile(3233, 3226, 0),
            new Tile(3233, 3220, 0),
            new Tile(3229, 3218, 0),
            new Tile(3220, 3218, 0),
            new Tile(3214, 3223, 0),
            new Tile(3215, 3227, 0),
            new Tile(3205, 3228, 0),
            new Tile(3205, 3228, 1),
            new Tile(3205, 3228, 2),
            new Tile(3206, 3218, 2),
            new Tile(3208, 3218, 2),
            new Tile(3208, 3219, 2)
    };
    public static final Tile [] DRAYNOR_VILLAGE_WILLOW_PATH_1 = {
            new Tile(3092, 3241, 0), new Tile(3092, 3245, 0),
            new Tile(3092, 3247, 0), new Tile(3088, 3248, 0),
            new Tile(3086, 3246, 0), new Tile(3087, 3243, 0),
            new Tile(3087, 3240, 0), new Tile(3087, 3236, 0),
            new Tile(3088, 3236, 0)
    };
    public static final Tile [] DRAYNOR_VILLAGE_WILLOW_PATH_2 = {
            new Tile(3092, 3242, 0),
            new Tile(3092, 3245, 0),
            new Tile(3093, 3247, 0),
            new Tile(3093, 3248, 0),
            new Tile(3087, 3249, 0),
            new Tile(3081, 3243, 0),
            new Tile(3082, 3239, 0),
            new Tile(3086, 3237, 0)
    };
    public static final Tile [] DRAYNOR_VILLAGE_WILLOW_PATH_3 = {
            new Tile(3092, 3242, 0),
            new Tile(3092, 3246, 0),
            new Tile(3096, 3247, 0),
            new Tile(3099, 3245, 0),
            new Tile(3097, 3237, 0),
            new Tile(3091, 3235, 0)
    };

    public static Tile [] DRAYNOR_PATH() {
        int randomizePath = Random.nextInt(1, 3);

        if(randomizePath == 1)
            return DRAYNOR_VILLAGE_WILLOW_PATH_1;
        if(randomizePath == 2)
            return DRAYNOR_VILLAGE_WILLOW_PATH_2;
        if(randomizePath == 3)
            return DRAYNOR_VILLAGE_WILLOW_PATH_3;

        return DRAYNOR_VILLAGE_WILLOW_PATH_1;
    }

    private static Tile [] randomPath(Tile [][] ARRAY_OF_PATHS) {
        int pathRow = 0;
        Tile[] path = {};

        pathRow = Random.nextInt(0, ARRAY_OF_PATHS.length - 1);
        path = new Tile[ARRAY_OF_PATHS[pathRow].length];
        for(int i = 0; i < ARRAY_OF_PATHS[pathRow].length; i++) {
            path[i] = ARRAY_OF_PATHS[pathRow][i];
        }

        return path;
    }
}
