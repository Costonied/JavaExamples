import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.doReturn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.BeforeClass;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(VlcPlayerMinimal.class)
public class VlcPlayerMinimalTest {

    static VlcPlayerMinimal vlcPlayerSpy = PowerMockito.spy(new VlcPlayerMinimal());

    @BeforeClass
    public static void init() throws Exception {
        doReturn("{status: stopped, id: 2}").when(vlcPlayerSpy, "executeGetRequest", Mockito.any(), Mockito.any());
    }

    @Test
    public void getVlcRcStatusTest() {
//        VlcPlayerMinimal vlcPlayerSpy = PowerMockito.spy(new VlcPlayerMinimal());
        try {
//            doReturn("{status: stopped, id: 2}").when(vlcPlayerSpy, "executeGetRequest", Mockito.any(), Mockito.any());
            String vlcRcStatus = vlcPlayerSpy.getVlcRcStatus();
            System.out.println(vlcRcStatus);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unexpected exception thrown.");
        }
    }
}