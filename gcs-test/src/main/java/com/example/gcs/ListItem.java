package com.example.gcs;

/**
 * Created in gcs-fs on 6/25/17.
 */
public class ListItem {
  private String fileName;
  private String path;
  private boolean isDirectory;

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getFileName() {
    return fileName;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }

  public void setIsDirectory(boolean isDirectory) {
    this.isDirectory = isDirectory;
  }

  public boolean isDirectory() {
    return isDirectory;
  }

  public void setDirectory(boolean isDirectory) {
    this.isDirectory = isDirectory;
  }
}
