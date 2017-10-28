package util;

import com.uqbar.vainilla.appearances.Animation;
import com.uqbar.vainilla.appearances.SimpleAppearance;
import com.uqbar.vainilla.appearances.Sprite;

/**
 * Created by Pelotita on 28/10/2017.
 */
public class AparienceFactory {

    private static Animation bombAnim;
    private static Sprite sprites;

    static public Animation fetchAppearance() {
        if (bombAnim== null){

            Sprite[] sprites = new Sprite[]{
                    Sprite.fromImage("content.img/14bomberman.PNG").crop(19, 254, 14, 28).scale(2.80, 1.65),
                    Sprite.fromImage("content.img/14bomberman.PNG").crop(33, 254, 16, 28).scale(2.80, 1.65),
                    Sprite.fromImage("content.img/14bomberman.PNG").crop(49, 254, 18, 28).scale(2.80, 1.65),
                    Sprite.fromImage("content.img/14bomberman.PNG").crop(33, 254, 16, 28).scale(2.80, 1.65)
            };
            bombAnim = new Animation(0.125, sprites);
        }
        return bombAnim;
    }
}
