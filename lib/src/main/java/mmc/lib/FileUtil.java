package mmc.lib;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.graphics.Color;
import android.view.View;
import android.provider.DocumentsContract;

public class FileUtil {
private static void createNewFile(String path) {
int lastSep = path.lastIndexOf(File.separator);
if (lastSep > 0) {
String dirPath = path.substring(0, lastSep);
makeDir(dirPath);
}

File file = new File(path);

try {
if (!file.exists())
file.createNewFile();
} catch (IOException e) {
e.printStackTrace();
}}

public static String readFile(String path) {
createNewFile(path);

StringBuilder sb = new StringBuilder();
FileReader fr = null;
try {
fr = new FileReader(new File(path));

char[] buff = new char[1024];
int length = 0;

while ((length = fr.read(buff)) > 0) {
sb.append(new String(buff, 0, length));
}} catch (IOException e) {
e.printStackTrace();
} finally {
if (fr != null) {
try {
fr.close();
} catch (Exception e) {
e.printStackTrace();
}}}

return sb.toString();
}

public static void writeFile(String path, String str) {
createNewFile(path);
FileWriter fileWriter = null;

try {
fileWriter = new FileWriter(new File(path), false);
fileWriter.write(str);
fileWriter.flush();
} catch (IOException e) {
e.printStackTrace();
} finally {
try {
if (fileWriter != null)
fileWriter.close();
} catch (IOException e) {
e.printStackTrace();
}}}

public static void copyFile(String sourcePath, String destPath) {
if (!isExistFile(sourcePath)) return;
createNewFile(destPath);

FileInputStream fis = null;
FileOutputStream fos = null;

try {
fis = new FileInputStream(sourcePath);
fos = new FileOutputStream(destPath, false);

byte[] buff = new byte[1024];
int length = 0;

while ((length = fis.read(buff)) > 0) {
fos.write(buff, 0, length);
}} catch (IOException e) {
e.printStackTrace();
} finally {
if (fis != null) {
try {
fis.close();
} catch (IOException e) {
e.printStackTrace();
}}
if (fos != null) {
try {
fos.close();
} catch (IOException e) {
e.printStackTrace();
}}}}

public static void moveFile(String sourcePath, String destPath) {
copyFile(sourcePath, destPath);
deleteFile(sourcePath);
}

public static void deleteFile(String path) {
File file = new File(path);

if (!file.exists()) return;

if (file.isFile()) {
file.delete();
return;
}

File[] fileArr = file.listFiles();

if (fileArr != null) {
for (File subFile : fileArr) {
if (subFile.isDirectory()) {
deleteFile(subFile.getAbsolutePath());
}

if (subFile.isFile()) {
subFile.delete();
}}}

file.delete();
}

public static boolean isExistFile(String path) {
File file = new File(path);
return file.exists();
}

public static void makeDir(String path) {
if (!isExistFile(path)) {
File file = new File(path);
file.mkdirs();
}}

public static void listDir(String path, ArrayList<String> list) {
File dir = new File(path);
if (!dir.exists() || dir.isFile()) return;

File[] listFiles = dir.listFiles();
if (listFiles == null || listFiles.length <= 0) return;

if (list == null) return;
list.clear();
for (File file : listFiles) {
list.add(file.getAbsolutePath());
}}

public static boolean isDirectory(String path) {
if (!isExistFile(path)) return false;
return new File(path).isDirectory();
}

public static boolean isFile(String path) {
if (!isExistFile(path)) return false;
return new File(path).isFile();
}

public static long getFileLength(String path) {
if (!isExistFile(path)) return 0;
return new File(path).length();
}

public static String getExternalStorageDir() {
return Environment.getExternalStorageDirectory().getAbsolutePath();
}

public static String getPackageDataDir(Context context) {
return context.getExternalFilesDir(null).getAbsolutePath();
}

public static String getPublicDir(String type) {
return Environment.getExternalStoragePublicDirectory(type).getAbsolutePath();
}

private static void saveBitmap(Bitmap bitmap, String destPath) {
FileOutputStream out = null;
FileUtil.createNewFile(destPath);
try {
out = new FileOutputStream(new File(destPath));
bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
} catch (Exception e) {
e.printStackTrace();
} finally {
try {
if (out != null) {
out.close();
}} catch (IOException e) {
e.printStackTrace();
}}}

public static Bitmap getScaledBitmap(String path, int max) {
Bitmap src = BitmapFactory.decodeFile(path);

int width = src.getWidth();
int height = src.getHeight();
float rate = 0.0f;

if (width > height) {
rate = max / (float) width;
height = (int) (height * rate);
width = max;
} else {
rate = max / (float) height;
width = (int) (width * rate);
height = max;
}

return Bitmap.createScaledBitmap(src, width, height, true);
}

public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
final int width = options.outWidth;
final int height = options.outHeight;
int inSampleSize = 1;

if (height > reqHeight || width > reqWidth) {
final int halfHeight = height / 2;
final int halfWidth = width / 2;

while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
inSampleSize *= 2;
}}

return inSampleSize;
}

public static Bitmap decodeSampleBitmapFromPath(String path, int reqWidth, int reqHeight) {
final BitmapFactory.Options options = new BitmapFactory.Options();
options.inJustDecodeBounds = true;
BitmapFactory.decodeFile(path, options);

options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

options.inJustDecodeBounds = false;
return BitmapFactory.decodeFile(path, options);
}

public static void resizeBitmapFileRetainRatio(String fromPath, String destPath, int max) {
if (!isExistFile(fromPath)) return;
Bitmap bitmap = getScaledBitmap(fromPath, max);
saveBitmap(bitmap, destPath);
}

public static void resizeBitmapFileToSquare(String fromPath, String destPath, int max) {
if (!isExistFile(fromPath)) return;
Bitmap src = BitmapFactory.decodeFile(fromPath);
Bitmap bitmap = Bitmap.createScaledBitmap(src, max, max, true);
saveBitmap(bitmap, destPath);
}

public static void resizeBitmapFileToCircle(String fromPath, String destPath) {
if (!isExistFile(fromPath)) return;
Bitmap src = BitmapFactory.decodeFile(fromPath);
Bitmap bitmap = Bitmap.createBitmap(src.getWidth(),
src.getHeight(), Bitmap.Config.ARGB_8888);
Canvas canvas = new Canvas(bitmap);

final int color = 0xff424242;
final Paint paint = new Paint();
final Rect rect = new Rect(0, 0, src.getWidth(), src.getHeight());

paint.setAntiAlias(true);
canvas.drawARGB(0, 0, 0, 0);
paint.setColor(color);
canvas.drawCircle(src.getWidth() / 2, src.getHeight() / 2,
src.getWidth() / 2, paint);
paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
canvas.drawBitmap(src, rect, rect, paint);

saveBitmap(bitmap, destPath);
}

public static void resizeBitmapFileWithRoundedBorder(String fromPath, String destPath, int pixels) {
if (!isExistFile(fromPath)) return;
Bitmap src = BitmapFactory.decodeFile(fromPath);
Bitmap bitmap = Bitmap.createBitmap(src.getWidth(), src
.getHeight(), Bitmap.Config.ARGB_8888);
Canvas canvas = new Canvas(bitmap);

final int color = 0xff424242;
final Paint paint = new Paint();
final Rect rect = new Rect(0, 0, src.getWidth(), src.getHeight());
final RectF rectF = new RectF(rect);
final float roundPx = pixels;

paint.setAntiAlias(true);
canvas.drawARGB(0, 0, 0, 0);
paint.setColor(color);
canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
canvas.drawBitmap(src, rect, rect, paint);

saveBitmap(bitmap, destPath);
}

public static void cropBitmapFileFromCenter(String fromPath, String destPath, int w, int h) {
if (!isExistFile(fromPath)) return;
Bitmap src = BitmapFactory.decodeFile(fromPath);

int width = src.getWidth();
int height = src.getHeight();

if (width < w && height < h)
return;

int x = 0;
int y = 0;

if (width > w)
x = (width - w) / 2;

if (height > h)
y = (height - h) / 2;

int cw = w;
int ch = h;

if (w > width)
cw = width;

if (h > height)
ch = height;

Bitmap bitmap = Bitmap.createBitmap(src, x, y, cw, ch);
saveBitmap(bitmap, destPath);
}

public static void rotateBitmapFile(String fromPath, String destPath, float angle) {
if (!isExistFile(fromPath)) return;
Bitmap src = BitmapFactory.decodeFile(fromPath);
Matrix matrix = new Matrix();
matrix.postRotate(angle);
Bitmap bitmap = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
saveBitmap(bitmap, destPath);
}

public static void scaleBitmapFile(String fromPath, String destPath, float x, float y) {
if (!isExistFile(fromPath)) return;
Bitmap src = BitmapFactory.decodeFile(fromPath);
Matrix matrix = new Matrix();
matrix.postScale(x, y);

int w = src.getWidth();
int h = src.getHeight();

Bitmap bitmap = Bitmap.createBitmap(src, 0, 0, w, h, matrix, true);
saveBitmap(bitmap, destPath);
}

public static void skewBitmapFile(String fromPath, String destPath, float x, float y) {
if (!isExistFile(fromPath)) return;
Bitmap src = BitmapFactory.decodeFile(fromPath);
Matrix matrix = new Matrix();
matrix.postSkew(x, y);

int w = src.getWidth();
int h = src.getHeight();

Bitmap bitmap = Bitmap.createBitmap(src, 0, 0, w, h, matrix, true);
saveBitmap(bitmap, destPath);
}

public static void setBitmapFileColorFilter(String fromPath, String destPath, int color) {
if (!isExistFile(fromPath)) return;
Bitmap src = BitmapFactory.decodeFile(fromPath);
Bitmap bitmap = Bitmap.createBitmap(src, 0, 0,
src.getWidth() - 1, src.getHeight() - 1);
Paint p = new Paint();
ColorFilter filter = new LightingColorFilter(color, 1);
p.setColorFilter(filter);
Canvas canvas = new Canvas(bitmap);
canvas.drawBitmap(bitmap, 0, 0, p);
saveBitmap(bitmap, destPath);
}

public static void setBitmapFileBrightness(String fromPath, String destPath, float brightness) {
if (!isExistFile(fromPath)) return;
Bitmap src = BitmapFactory.decodeFile(fromPath);
ColorMatrix cm = new ColorMatrix(new float[]
{
1, 0, 0, 0, brightness,
0, 1, 0, 0, brightness,
0, 0, 1, 0, brightness,
0, 0, 0, 1, 0
});

Bitmap bitmap = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
Canvas canvas = new Canvas(bitmap);
Paint paint = new Paint();
paint.setColorFilter(new ColorMatrixColorFilter(cm));
canvas.drawBitmap(src, 0, 0, paint);
saveBitmap(bitmap, destPath);
}

public static void setBitmapFileContrast(String fromPath, String destPath, float contrast) {
if (!isExistFile(fromPath)) return;
Bitmap src = BitmapFactory.decodeFile(fromPath);
ColorMatrix cm = new ColorMatrix(new float[]
{
contrast, 0, 0, 0, 0,
0, contrast, 0, 0, 0,
0, 0, contrast, 0, 0,
0, 0, 0, 1, 0
});

Bitmap bitmap = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
Canvas canvas = new Canvas(bitmap);
Paint paint = new Paint();
paint.setColorFilter(new ColorMatrixColorFilter(cm));
canvas.drawBitmap(src, 0, 0, paint);

saveBitmap(bitmap, destPath);
}

public static int getJpegRotate(String filePath) {
int rotate = 0;
try {
ExifInterface exif = new ExifInterface(filePath);
int iOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);

switch (iOrientation) {
case ExifInterface.ORIENTATION_ROTATE_90:
rotate = 90;
break;
case ExifInterface.ORIENTATION_ROTATE_180:
rotate = 180;
break;
case ExifInterface.ORIENTATION_ROTATE_270:
rotate = 270;
break;
default:
rotate = 0;
break;
}}
catch (IOException e) {
return 0;
}
return rotate;
}
public static File createNewPictureFile(Context context) {
SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd_HHmmss");
String fileName = date.format(new Date()) + ".jpg";
File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DCIM).getAbsolutePath() + File.separator + fileName);
return file;
}
}
