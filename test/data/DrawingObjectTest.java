package data;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by minchul on 2017-06-09.
 */
@RunWith(Arquillian.class)
public class DrawingObjectTest {
    @Test
    public void getX() throws Exception {
    }

    @Test
    public void getY() throws Exception {
    }

    @Test
    public void getImage() throws Exception {
    }

    @Test
    public void getWidth() throws Exception {
    }

    @Test
    public void getHeight() throws Exception {
    }

    @Test
    public void move() throws Exception {
    }

    @Test
    public void checkCrush() throws Exception {
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(DrawingObject.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
