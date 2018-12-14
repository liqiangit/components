import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

public class SwingDemoRenameTest {
	public static void copyFile(String oldPath, String newPath) throws IOException {
        File oldFile = new File(oldPath);
        File file = new File(newPath);
        FileInputStream in = new FileInputStream(oldFile);
        FileOutputStream out = new FileOutputStream(file);;

        byte[] buffer=new byte[2097152];
        int readByte = 0;
        while((readByte = in.read(buffer)) != -1){
            out.write(buffer, 0, readByte);
        }
    
        in.close();
        out.close();
    }
	public static void copyDir(String sourcePath, String newPath) throws IOException {
        File file = new File(sourcePath);
        String[] filePath = file.list();
        
        if (!(new File(newPath)).exists()) {
            (new File(newPath)).mkdir();
        }
        
        for (int i = 0; i < filePath.length; i++) {
            if ((new File(sourcePath + file.separator + filePath[i])).isDirectory()) {
                copyDir(sourcePath  + file.separator  + filePath[i], newPath  + file.separator + filePath[i]);
            }
            
            if (new File(sourcePath  + file.separator + filePath[i]).isFile()) {
                copyFile(sourcePath + file.separator + filePath[i], newPath + file.separator + filePath[i]);
            }
            
        }
    }
public static void main(String[] args) {
	File file=new File("E:\\迅雷下载\\components");
	File[] files=file.listFiles();
	for (File file2 : files) {
		File[] srcs= file2.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return "src".equals(name);
			}
		});
		for (File file3 : srcs) {
			System.out.println(file3.getParentFile().getName());
			String str=file3.getAbsolutePath().replace(file3.getParentFile().getName()+"\\", "");
			try {
				copyDir(file3.getAbsolutePath(), str);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			System.out.println(str);
//			file3.renameTo(new File(str));
		}
	}
}
}
