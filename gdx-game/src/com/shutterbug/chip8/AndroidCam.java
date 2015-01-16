import com.badlogic.gdx.graphics.OrthographicCamera;

public class AndroidCam extends OrthographicCamera{

    public AndroidCam(int width, int height){
        super(width, height);
        this.position.x=width/2;
        this.position.y=height/2;
    }
}
