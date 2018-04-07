package training;

import it.zielke.moji.MossException;
import it.zielke.moji.SocketClient;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;

public class MossInteractor  {

    public URL getMossResult() throws MossException, IOException {
        Collection<File> Files = FileUtils.listFiles(new File("testfiles"), new String[]{"py"}, true);

        SocketClient socketClient = new SocketClient();
        socketClient.setUserID("124190513");
        //socketClient.setUserID("123456");
        socketClient.setLanguage("python");
        socketClient.run();

        for(File fi : Files){
            socketClient.uploadFile(fi);
        }

        socketClient.sendQuery();
        URL scoreResult = socketClient.getResultURL();
        return scoreResult;
    }

    public void callMoss() throws IOException, MossException {
        MossInteractor mi = new MossInteractor();
        URL result = mi.getMossResult();
        System.out.println(result.toString());
    }
    public static void main(String[] args) throws IOException, MossException {

        new MossInteractor().callMoss();

    }


}
