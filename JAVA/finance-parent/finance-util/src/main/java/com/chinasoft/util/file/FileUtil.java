package com.chinasoft.util.file;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import cn.hutool.core.util.RandomUtil;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.chinasoft.util.common.DateUtil;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileUtil {
	
	protected static Logger logger = Logger.getLogger(FileUtil.class);

	public static String uploadFile(MultipartFile file,String uploadDir) throws IOException{

		String fileName = file.getOriginalFilename();
		String ext = fileName.substring(fileName.lastIndexOf("."));
		String filePath = dateToString("/yyyy/MM/dd/",new Date())+ RandomUtil.simpleUUID() + ext;
		// 复制文件
		FileUtils.forceMkdirParent(new File(uploadDir + filePath));
		FileUtils.copyInputStreamToFile(file.getInputStream(), new File(uploadDir+filePath));
		return filePath;
	}


	public  static void download(String path, HttpServletResponse response) throws IOException{
		// path是指欲下载的文件的路径。
		File file = new File(path);
		// 取得文件名。
		String filename = path.substring(path.lastIndexOf("/")+1);
		response.addHeader("Content-Disposition","inline;filename="+filename);
		if (file.exists()) {
			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					os.write(buffer, 0, i);
					i = bis.read(buffer);
				}
				System.out.println("download success");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static String dateToString(String pattern, Date srcDate) {
		return srcDate == null ? "" : new SimpleDateFormat(pattern).format(srcDate);
	}


		// 在该方法中：
	// 1. 创建文件时，先判断文件的父目录是否存在，不存在则创建父目录
	// 2. 创建文件时，先判断该文件是否存在，不存在则创建该文件
	// 3. 不同操作系统对文件目录分隔符处理存在差异，必须使用 File.separator 来统一处理。
	public static File createFile(String strFilePath, String strFileName) throws IOException {
		
		File filePath = new File(strFilePath);
		File fullFilePath = null;
		if(File.separator.equals(strFilePath.substring(strFilePath.length()-1, strFilePath.length()))) {
			fullFilePath = new File(strFilePath + strFileName);
		} else {
			fullFilePath = new File(strFilePath + File.separator + strFileName);
		}
		
		if(!filePath.exists()) {
			filePath.mkdirs();
		}
		
		if(!fullFilePath.exists()) {
			fullFilePath.createNewFile();
		}
		
		return fullFilePath;
	}
	
	/** 
	* 复制单个文件 
	* @param oldFullPath String 原文件路径 如：c:/fqf.txt 
	* @param newFullPath String 复制后路径 如：f:/fqf.txt 
	* @return boolean 
	*/
	public static String copyFile(String oldFullPath, String newFullPath) {
		
		int bytesum = 0;
		int byteread = 0;
		File oldFile = new File(oldFullPath);
		File newFile = new File(newFullPath);
		byte[] buffer = new byte[1444];
		InputStream inStream = null;
		FileOutputStream fs = null;
		
		if (!oldFile.exists()) {
			return null;
		}
		
		try {
			if (!newFile.exists()) {
				FileUtil.createFile(newFile.getParent(), newFile.getName());
			}
			
			inStream = new FileInputStream(oldFullPath);
			fs = new FileOutputStream(newFullPath);
			while ( (byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread;
				fs.write(buffer, 0, byteread);
			}
		} catch (FileNotFoundException e) {
			logger.error("文件复制异常：找不到源文件或目标文件", e);
			newFullPath = null;
		} catch (IOException e) {
			logger.error("文件复制异常：IO流出错", e);
			newFullPath = null;
		} finally {
			try {
				fs.close();
				inStream.close();
			} catch (IOException e) {
				logger.error("文件复制异常：无法关闭文件IO流", e);
			}
		}
		
		return newFullPath;
	}
	
	public static boolean writeFile(String fileFullPath, String content, boolean appendMode) {

		FileWriter fw = null;
		boolean returnVal = false;
		try {
			fw = new FileWriter(fileFullPath, appendMode);
			fw.write(content);
			
			returnVal = true;
		} catch (IOException e) {
			logger.error("文件写入异常：IO流出错", e);
			returnVal = false;
		} finally {
			try {
				fw.close();
			} catch (Exception e) {
				logger.error("文件写入异常：无法关闭文件IO流", e);
				returnVal = false;
			}
		}
		
		return returnVal;
	}
	
	// 先列举需要的文件操作公共方法
	public static boolean deleteFile(String fileFullPath) {
		
		return true;
	}
	
	public static boolean deleteStaleFile(String path, long makeSureSpace, int nDays) throws IOException{
		File folder = new File(path);
		if (!folder.exists()) {
			throw new IOException();
		}
		File[] files = folder.listFiles();
		
		if (nDays < 1 || folder.getUsableSpace() < makeSureSpace ) {
			nDays = 1;
		}
		// 86400000 = 24*60*60*1000;
		long overdueTime = new Date().getTime() - nDays * 86400000;

		for (File file : files) {
			if (overdueTime > file.lastModified()) {
				file.delete();
			}
		}
		if (folder.getUsableSpace() < makeSureSpace) {
			return false;
		} else {
			return true;
		}
	}
	
	public static void main(String[] args) throws Exception {
		FileUtil fileUtil = new FileUtil();
		
		String resourcePath = fileUtil.getClass().getClassLoader().getResource("").getPath();
		
		String fileFullPath = "";
		String fileNameWithTimestamp = "";
		String fileParentPath = resourcePath + "config-util/report_template/";
		String templateFileFullPath = fileParentPath + "report_template.xlsx";
		
		File newFile = new File(templateFileFullPath);
		
		String[] targetFileNameSplit = newFile.getName().split("\\.");
		String reportNamePrefix = targetFileNameSplit[0];
		fileNameWithTimestamp = DateUtil.fileNameWithCurrentTimestamp(reportNamePrefix, "20170119122409937");
		
		String defaultParentPath = "C:/workspace/common_framework/reports/";
		String defaultSuffix = ".xlsx";
		
		fileFullPath = defaultParentPath + fileNameWithTimestamp + defaultSuffix;
		
		FileUtil.copyFile(templateFileFullPath, fileFullPath);
	}
	
	public static byte[] inputStreamToByteArray(InputStream inStream)
			throws IOException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int rc = 0;
		while ((rc = inStream.read(buff, 0, 100)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		byte[] in2b = swapStream.toByteArray();
		return in2b;
	}
	
	public static boolean Base64ToImage(byte[] byteImg, String fileFullPath){
		try {
			OutputStream out = new FileOutputStream(fileFullPath); 
			out.write(byteImg);
			out.flush();
			out.close();
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public static boolean BtyeToImage(byte[] byteArray, String fileFullPath) {
		try {
			for(int i=0;i<byteArray.length;++i) {
				if(byteArray[i]<0)
				{//调整异常数据
					byteArray[i]+=256;
				}
			}
			
			OutputStream out = new FileOutputStream(fileFullPath); 
			out.write(byteArray);
			out.flush();
			out.close();
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}


	/**
	 * 验证图片格式
	 * @param request
	 * @param out
	 * @param fileName
	 * @return
	 */
	public static boolean valiImage(HttpServletRequest request, PrintWriter out, String fileName) {
		String callback = request.getParameter("CKEditorFuncNum");
		if (!fileName.endsWith(".jpg") && !fileName.endsWith(".JPG")
				&& !fileName.endsWith(".jpeg") && !fileName.endsWith(".JPEG")
				&& !fileName.endsWith(".bmp") && !!fileName.endsWith(".BMP")
				&& !fileName.endsWith(".gif") && !fileName.endsWith(".GIF")
				&& !fileName.endsWith(".png") && !fileName.endsWith(".PNG")) {

			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback +",''," + "'文件格式不正确（必须为.jpg/.gif/.png/.bmp）'");
			out.println("</script>");
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 多文件上传
	 * @param request
	 * @param response
	 * @param filePath
	 * @param filePathDir
	 * @param fileNum 数据库已经存在的文件数量
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> uploadFileList(HttpServletRequest request,HttpServletResponse response,String filePath,String filePathDir,int fileNum) throws Exception {
		String fileListPath = "";
		Map<String,Object> resultMap = new HashMap<>();
		// TODO Auto-generated method stub
		//如果数据库中文件数量+前端已经上传成功的数量已经达到5，则不再上传
		//上传过程中达到5，也不再继续上传
		System.out.println("前端数量------------------"+fileNum);
		if (fileNum >= 5) {
			resultMap.put("3","上传文件数量超过5个");
			return resultMap;
		} else {
            //创建一个通用的多部分解析器
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			//判断 request 是否有文件上传,即多部分请求
			if (multipartResolver.isMultipart(request)) {
				//转换成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				//取得request中的所有文件名
				Iterator<String> iter = multiRequest.getFileNames();


				while (iter.hasNext()) {

						//取得上传文件
						MultipartFile file = multiRequest.getFile(iter.next());
						if (file != null) {
							//取得当前上传文件的文件名称
							String myFileName = file.getOriginalFilename();
							//如果名称不为“”,说明该文件存在，否则说明该文件不存在
							if (myFileName.trim() != "") {
								System.out.println(myFileName);
								if (myFileName.indexOf(",") != -1 || myFileName.indexOf("，") != -1) {
									resultMap.put("2","上传文件名称不能含有逗号");
									return resultMap;
								}
								//自定义数字加字母产生随机数作为文件名
								String oldFileName = Integer.toHexString(new Random().nextInt()) + myFileName;
								String fileName = oldFileName.replace(",","");
								//定义上传路径
								try {
									String pat = filePathDir + fileName;//保存在数据库的路径
									fileListPath = pat + ",";
									File localFile = FileUtil.createFile(filePath, fileName);//保存的物理地址
									file.transferTo(localFile);

								} catch (IllegalStateException e) {

									e.printStackTrace();
									resultMap.put("1","上传错误");
									return resultMap;
								} catch (IOException e) {
									e.printStackTrace();
									resultMap.put("1","上传错误");
									return resultMap;
								}
							}
						}

				}

			}
		}


		System.out.println("-------------------------"+fileListPath);

		resultMap.put("0",fileListPath);
		return resultMap;

	}


	/**
	 * 格式化上传文件名称，去掉特殊符号
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static  String formatFilename(String fileName){
       if (fileName.indexOf(",") != -1) {
       	fileName = fileName.replace(",","");
	   }
	   if (fileName.indexOf("，") != -1) {
       	fileName = fileName.replace("，","");
	   }
	   if (fileName.indexOf("&") != -1) {
       	fileName = fileName.replace("&","");
	   }
	   if (fileName.indexOf("=") != -1) {
       	fileName = fileName.replace("=","");
	   }
	   if (fileName.indexOf("?") != -1) {
       	fileName = fileName.replace("?","");
	   }
	   if (fileName.indexOf("？") != -1) {
       	fileName = fileName.replace("？","");
	   }
	   if (fileName.indexOf("/") != -1) {
       	fileName = fileName.replace("/","");
	   }
	   if (fileName.indexOf("%") != -1) {
       	fileName = fileName.replace("%","");
	   }
	   return fileName;
	}
}
