package game.battle.components;

import com.uqbar.vainilla.appearances.Animation;
import com.uqbar.vainilla.appearances.Sprite;

/**
 * Created by Pelotita on 28/10/2017.
 */
public class PlayerState {
    public static int STANDING = 0;
    public static int WALKING_LEFT = 1;
    public static int WALKING_TOP = 2;
    public static int WALKING_RIGHT = 3;
    public static int WALKING_DOWN = 4;
    public static int DYING = 5;


    public Animation[] animations = new Animation[6];

    public PlayerState(int wearColor){
        Sprite[] standing = new Sprite[]{
                Sprite.fromImage("content.img/14bomberman.PNG").crop(68, 37+(wearColor * 27), 19, 28).scale(2.80, 1.65),
        };
        Sprite[] walkingLeft = new Sprite[]{
                Sprite.fromImage("content.img/14bomberman.PNG").crop(104, 37+(wearColor * 27), 19, 28).scale(2.80, 1.65),
                Sprite.fromImage("content.img/14bomberman.PNG").crop(122, 37+(wearColor * 27), 19, 28).scale(2.80, 1.65),
                Sprite.fromImage("content.img/14bomberman.PNG").crop(140, 37+(wearColor * 27), 19, 28).scale(2.80, 1.65),
                Sprite.fromImage("content.img/14bomberman.PNG").crop(122, 37+(wearColor * 27), 19, 28).scale(2.80, 1.65)
        };
        Sprite[] walkingRight = new Sprite[]{
                Sprite.fromImage("content.img/14bomberman.PNG").crop(159, 37+(wearColor * 27), 19, 28).scale(2.80, 1.65),
                Sprite.fromImage("content.img/14bomberman.PNG").crop(177, 37+(wearColor * 27), 19, 28).scale(2.80, 1.65),
                Sprite.fromImage("content.img/14bomberman.PNG").crop(195, 37+(wearColor * 27), 19, 28).scale(2.80, 1.65),
                Sprite.fromImage("content.img/14bomberman.PNG").crop(177, 37+(wearColor * 27), 19, 28).scale(2.80, 1.65)
        };
        Sprite[] walkingTop = new Sprite[]{
                Sprite.fromImage("content.img/14bomberman.PNG").crop(217, 37+(wearColor * 27), 19, 28).scale(2.80, 1.65),
                Sprite.fromImage("content.img/14bomberman.PNG").crop(235, 37+(wearColor * 27), 19, 28).scale(2.80, 1.65),
                Sprite.fromImage("content.img/14bomberman.PNG").crop(253, 37+(wearColor * 27), 19, 28).scale(2.80, 1.65),
                Sprite.fromImage("content.img/14bomberman.PNG").crop(235, 37+(wearColor * 27), 19, 28).scale(2.80, 1.65)
        };
        Sprite[] walkingDown = new Sprite[]{
                Sprite.fromImage("content.img/14bomberman.PNG").crop(50, 37+(wearColor * 27), 19, 28).scale(2.80, 1.65),
                Sprite.fromImage("content.img/14bomberman.PNG").crop(68, 37+(wearColor * 27), 19, 28).scale(2.80, 1.65),
                Sprite.fromImage("content.img/14bomberman.PNG").crop(86, 37+(wearColor * 27), 19, 28).scale(2.80, 1.65),
                Sprite.fromImage("content.img/14bomberman.PNG").crop(68, 37+(wearColor * 27), 19, 28).scale(2.80, 1.65)
        };
        Sprite[] dying = new Sprite[]{
                Sprite.fromImage("content.img/14bomberman.PNG").crop(320, 37+(wearColor * 27), 19, 28).scale(2.80, 1.65),
                Sprite.fromImage("content.img/14bomberman.PNG").crop(338, 37+(wearColor * 27), 19, 28).scale(2.80, 1.65),
                Sprite.fromImage("content.img/14bomberman.PNG").crop(356, 37+(wearColor * 27), 19, 28).scale(2.80, 1.65),
                Sprite.fromImage("content.img/14bomberman.PNG").crop(374, 37+(wearColor * 27), 19, 28).scale(2.80, 1.65)
        };
        animations[STANDING] = new Animation(0.125, standing);
        animations[WALKING_LEFT] = new Animation(0.125, walkingLeft);
        animations[WALKING_TOP] = new Animation(0.125, walkingTop);
        animations[WALKING_RIGHT] = new Animation(0.125, walkingRight);
        animations[WALKING_DOWN] = new Animation(0.125, walkingDown);
        animations[DYING] = new Animation(0.125, dying);
    }
}
