package apiModels;

import com.fasterxml.jackson.annotation.*;
import java.util.Set;
import javax.validation.*;
import java.util.Objects;
/**
 * VocabularyFromGit
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-10-02T16:23:40.254+02:00")

@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
public class VocabularyFromGit   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("path")
  private String path = null;

  @JsonProperty("sha")
  private String sha = null;

  @JsonProperty("size")
  private String size = null;

  @JsonProperty("url")
  private String url = null;

  @JsonProperty("html_url")
  private String htmlUrl = null;

  @JsonProperty("git_url")
  private String gitUrl = null;

  @JsonProperty("download_url")
  private String downloadUrl = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("content")
  private String content = null;

  @JsonProperty("encoding")
  private String encoding = null;

  @JsonProperty("_links")
  private Object links = null;

  public VocabularyFromGit name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public VocabularyFromGit path(String path) {
    this.path = path;
    return this;
  }

   /**
   * Get path
   * @return path
  **/
    public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public VocabularyFromGit sha(String sha) {
    this.sha = sha;
    return this;
  }

   /**
   * Get sha
   * @return sha
  **/
    public String getSha() {
    return sha;
  }

  public void setSha(String sha) {
    this.sha = sha;
  }

  public VocabularyFromGit size(String size) {
    this.size = size;
    return this;
  }

   /**
   * Get size
   * @return size
  **/
    public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public VocabularyFromGit url(String url) {
    this.url = url;
    return this;
  }

   /**
   * Get url
   * @return url
  **/
    public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public VocabularyFromGit htmlUrl(String htmlUrl) {
    this.htmlUrl = htmlUrl;
    return this;
  }

   /**
   * Get htmlUrl
   * @return htmlUrl
  **/
    public String getHtmlUrl() {
    return htmlUrl;
  }

  public void setHtmlUrl(String htmlUrl) {
    this.htmlUrl = htmlUrl;
  }

  public VocabularyFromGit gitUrl(String gitUrl) {
    this.gitUrl = gitUrl;
    return this;
  }

   /**
   * Get gitUrl
   * @return gitUrl
  **/
    public String getGitUrl() {
    return gitUrl;
  }

  public void setGitUrl(String gitUrl) {
    this.gitUrl = gitUrl;
  }

  public VocabularyFromGit downloadUrl(String downloadUrl) {
    this.downloadUrl = downloadUrl;
    return this;
  }

   /**
   * Get downloadUrl
   * @return downloadUrl
  **/
    public String getDownloadUrl() {
    return downloadUrl;
  }

  public void setDownloadUrl(String downloadUrl) {
    this.downloadUrl = downloadUrl;
  }

  public VocabularyFromGit type(String type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
    public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public VocabularyFromGit content(String content) {
    this.content = content;
    return this;
  }

   /**
   * Get content
   * @return content
  **/
    public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public VocabularyFromGit encoding(String encoding) {
    this.encoding = encoding;
    return this;
  }

   /**
   * Get encoding
   * @return encoding
  **/
    public String getEncoding() {
    return encoding;
  }

  public void setEncoding(String encoding) {
    this.encoding = encoding;
  }

  public VocabularyFromGit links(Object links) {
    this.links = links;
    return this;
  }

   /**
   * Get links
   * @return links
  **/
    public Object getLinks() {
    return links;
  }

  public void setLinks(Object links) {
    this.links = links;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VocabularyFromGit vocabularyFromGit = (VocabularyFromGit) o;
    return Objects.equals(name, vocabularyFromGit.name) &&
        Objects.equals(path, vocabularyFromGit.path) &&
        Objects.equals(sha, vocabularyFromGit.sha) &&
        Objects.equals(size, vocabularyFromGit.size) &&
        Objects.equals(url, vocabularyFromGit.url) &&
        Objects.equals(htmlUrl, vocabularyFromGit.htmlUrl) &&
        Objects.equals(gitUrl, vocabularyFromGit.gitUrl) &&
        Objects.equals(downloadUrl, vocabularyFromGit.downloadUrl) &&
        Objects.equals(type, vocabularyFromGit.type) &&
        Objects.equals(content, vocabularyFromGit.content) &&
        Objects.equals(encoding, vocabularyFromGit.encoding) &&
        Objects.equals(links, vocabularyFromGit.links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, path, sha, size, url, htmlUrl, gitUrl, downloadUrl, type, content, encoding, links);
  }

  @SuppressWarnings("StringBufferReplaceableByString")
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VocabularyFromGit {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    sha: ").append(toIndentedString(sha)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    htmlUrl: ").append(toIndentedString(htmlUrl)).append("\n");
    sb.append("    gitUrl: ").append(toIndentedString(gitUrl)).append("\n");
    sb.append("    downloadUrl: ").append(toIndentedString(downloadUrl)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    encoding: ").append(toIndentedString(encoding)).append("\n");
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

