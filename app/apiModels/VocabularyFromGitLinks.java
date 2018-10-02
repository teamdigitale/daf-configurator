package apiModels;

import com.fasterxml.jackson.annotation.*;
import java.util.Set;
import javax.validation.*;
import java.util.Objects;
/**
 * VocabularyFromGitLinks
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-10-02T16:23:40.254+02:00")

@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
public class VocabularyFromGitLinks   {
  @JsonProperty("self")
  private String self = null;

  @JsonProperty("git")
  private String git = null;

  @JsonProperty("html")
  private String html = null;

  public VocabularyFromGitLinks self(String self) {
    this.self = self;
    return this;
  }

   /**
   * Get self
   * @return self
  **/
    public String getSelf() {
    return self;
  }

  public void setSelf(String self) {
    this.self = self;
  }

  public VocabularyFromGitLinks git(String git) {
    this.git = git;
    return this;
  }

   /**
   * Get git
   * @return git
  **/
    public String getGit() {
    return git;
  }

  public void setGit(String git) {
    this.git = git;
  }

  public VocabularyFromGitLinks html(String html) {
    this.html = html;
    return this;
  }

   /**
   * Get html
   * @return html
  **/
    public String getHtml() {
    return html;
  }

  public void setHtml(String html) {
    this.html = html;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VocabularyFromGitLinks vocabularyFromGitLinks = (VocabularyFromGitLinks) o;
    return Objects.equals(self, vocabularyFromGitLinks.self) &&
        Objects.equals(git, vocabularyFromGitLinks.git) &&
        Objects.equals(html, vocabularyFromGitLinks.html);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, git, html);
  }

  @SuppressWarnings("StringBufferReplaceableByString")
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VocabularyFromGitLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    git: ").append(toIndentedString(git)).append("\n");
    sb.append("    html: ").append(toIndentedString(html)).append("\n");
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

