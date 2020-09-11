package util;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

public class EmpUploadFileUtils {

	// public static String uploadFile(String uploadPath,
	// String originalName,
	// byte[] fileData)throws Exception{
	//
	// return null;
	// }
	//

	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {

		UUID uid = UUID.randomUUID();

		String savedName = uid.toString() + "_" + originalName;
		//String savedName = originalName;

		String savedPath = calcPath(uploadPath);

//		File target = new File(uploadPath + savedPath, savedName);
		//File target = new File(uploadPath, savedName);

//		FileCopyUtils.copy(fileData, target);
		FileIoCopy(uploadPath+originalName,uploadPath+savedPath+ File.separator +savedName);

		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);

		String uploadedFileName = null;

		if (MediaUtils.getMediaType(formatName) != null) {
			uploadedFileName = getOriginalPathName(uploadPath, savedPath, savedName);
			// uploadedFileName = getOriginalPathName(uploadPath, savedName);

			System.out.println("uploadPath : " + uploadPath);
			System.out.println("savedName : " + savedName);
			System.out.println("uploadedFileName : " + uploadedFileName);

			makeThumbnail(uploadPath, savedPath, savedName);
			// makeThumbnail(uploadPath, savedName);

			System.out.println("makeThumbnail return======> " + uploadedFileName);

		} else {
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
			// uploadedFileName = makeIcon(uploadPath, savedName);

			System.out.println(" makeIcon return======> " + uploadedFileName);
		}

		return uploadedFileName;

	}

	private static String makeIcon(String uploadPath, String path, String fileName) throws Exception {

		String iconName = uploadPath + path + File.separator + fileName;

		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

	/*
	 * private static String makeIcon(String uploadPath, String fileName) throws
	 * Exception {
	 * 
	 * String iconName = uploadPath + File.separator + fileName;
	 * 
	 * return
	 * iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	 * }
	 */

	/*
	 * private static String makeThumbnail(String uploadPath, String fileName)
	 * throws Exception {
	 * 
	 * BufferedImage sourceImg = ImageIO.read(new File(uploadPath, fileName));
	 * 
	 * BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC,
	 * Scalr.Mode.FIT_TO_HEIGHT, 300);
	 * 
	 * String thumbnailName = uploadPath + File.separator + "s_" + fileName;
	 * 
	 * File newFile = new File(thumbnailName); String formatName =
	 * fileName.substring(fileName.lastIndexOf(".") + 1);
	 * 
	 * ImageIO.write(destImg, formatName.toUpperCase(), newFile);
	 * 
	 * return
	 * thumbnailName.substring(uploadPath.length()).replace(File.separatorChar,
	 * '/'); }
	 */

	private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception {

		BufferedImage sourceImg = ImageIO.read(new File(uploadPath+path, fileName));

		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 300);

		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;

		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

		ImageIO.write(destImg, formatName.toUpperCase(), newFile);

		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

	/** */
	public static String getOriginalPathName(String uploadPath, String path, String fileName) {
		return (uploadPath + path + File.separator + fileName).substring(uploadPath.length())
				.replace(File.separatorChar, '/');
	}

	public static String getOriginalPathName(String uploadPath, String fileName) {
		return (uploadPath + File.separator + fileName).substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

	/** */

	private static String calcPath(String uploadPath) {

		Calendar cal = Calendar.getInstance();

		String yearPath = File.separator + cal.get(Calendar.YEAR);

		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);

		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));

		makeDir(uploadPath, yearPath, monthPath, datePath);

		return datePath;
	}

	private static void makeDir(String uploadPath, String... paths) {

		if (new File(paths[paths.length - 1]).exists()) {
			return;
		}

		for (String path : paths) {

			File dirPath = new File(uploadPath + path);

			if (!dirPath.exists()) {

				dirPath.mkdir();
			}
		}
	}

	
	// Java IO Stream Fullbuffer Copy
	public static void FileIoCopy(String source, String dest) throws FileNotFoundException, IOException {
	    try (FileInputStream fis = new FileInputStream(source);
	            FileOutputStream fos = new FileOutputStream(dest);) {
	        int availableLen = fis.available();
	        byte[] buf = new byte[availableLen];
	        fis.read(buf);
	        fos.write(buf);
	    }
	}
	 


}
