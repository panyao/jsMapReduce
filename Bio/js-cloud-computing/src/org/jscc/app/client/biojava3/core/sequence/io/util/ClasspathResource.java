//package org.jscc.app.client.biojava3.core.sequence.io.util;
//
//import static org.jscc.app.client.biojava3.core.sequence.io.util.IOUtils.close;
//import static org.jscc.app.client.biojava3.core.sequence.io.util.IOUtils.copy;
//
//import java.io.BufferedReader;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.List;
////import java.util.zip.GZIPInputStream;
//
//import org.jscc.app.client.biojava3.core.exceptions.ParserException;
//
///**
// * This object represents a classpath resource on the local system. It allows
// * you to specify a location and then extract the inputstream, reader or
// * lines of the resource. We also support GZiped files (so long as the resource
// * ends with a .gz) and pre-caching of the data so we read only once from
// * the classpath and close that link down. This is useful if you want to keep
// * IO handles down but not very useful if the file is very large.
// *
// * @author ayates
// *
// */
//public class ClasspathResource {
//
//  private final String location;
//  private final boolean preCache;
//  private final Boolean isGzip;
//
//  /**
//   * Basic constructor only allowing you to specify where to find the file.
//   *
//   * @param location Specified as <i>my/classpath/loc.txt</i>
//   */
//  public ClasspathResource(String location) {
//    this(location, false);
//  }
//
//  /**
//   * Advanced constructor which allows you to optionally pre-cache the
//   * data
//   *
//   * @param location Specified as <i>my/classpath/loc.txt</i>
//   * @param preCache If set to true will cause the data to be copied
//   * to an in memory byte array and then an InputStream will be wrapped around
//   * that.
//   */
//  public ClasspathResource(String location, boolean preCache) {
//    this.location = location;
//    this.preCache = preCache;
//    this.isGzip = null;
//  }
//
//  /**
//   * Advanced constructor which lets you set the preCache variable and to
//   * force the type of file we are decompressing. If this constructor is
//   * used we trust your call as to the file's compression status.
//   *
//   * @param location Specified as <i>my/classpath/loc.txt</i>
//   * @param preCache If set to true will cause the data to be copied
//   * to an in memory byte array and then an InputStream will be wrapped around
//   * that.
//   * @param isGzip Set to true or false if the file is gziped.
//   */
//  public ClasspathResource(String location, boolean preCache, boolean isGzip) {
//    this.location = location;
//    this.preCache = preCache;
//    this.isGzip = isGzip;
//  }
//
//  /**
//   * Returns the InputStream instance of this classpath resource
//   */
//  public InputStream getInputStream() {
//    return createClasspathInputStream();
//  }
//
//  /**
//   * Returns the reader representation of this classpath resource
//   */
//  public BufferedReader getBufferedReader() {
//    return new BufferedReader(new InputStreamReader(getInputStream()));
//  }
//
//  /**
//   * Returns this resource as a list of Strings
//   */
//  public List<String> getList() {
//    return IOUtils.getList(getBufferedReader());
//  }
//
//  private InputStream createClasspathInputStream() {
//    final InputStream is;
//    final InputStream classpathIs = getClass().getClassLoader().getResourceAsStream(location);    
//    if(classpathIs == null) {
//      throw new IllegalArgumentException("Location "+
//          location+" resulted in a null InputStream");
//    }
//    if(preCache) {
//      ByteArrayOutputStream os = new ByteArrayOutputStream();
//      try {
//        copy(classpathIs, os);
//      } catch (IOException e) {
//        throw new ParserException("Cannot copy classpath InputStream", e);
//      }
//      finally {
//        close(classpathIs);
//      }
//      is = new ByteArrayInputStream(os.toByteArray());
//    }
//    else {
//      is = classpathIs;
//    }
//    
//    /* 
//    if(isGzip()) {
//      try {
//        return new GZIPInputStream(is);
//      }
//      catch (IOException e) {
//        throw new ParserException("Cannot open stream as a GZIP stream", e);
//      }
//    }
//	*/
//    return is;
//  }
//
//  /**
//   * Returns true if the location given ends with a .gz extension. No magic
//   * number investigation is done.
//   */
//  private boolean isGzip() {
//    if(isGzip != null) {
//      return isGzip;
//    }
//    else {
//      return this.location.endsWith(".gz");
//    }
//  }
//}
