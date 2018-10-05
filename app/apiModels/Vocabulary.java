package apiModels;

import com.fasterxml.jackson.annotation.*;
import java.util.Set;
import javax.validation.*;
import java.util.Objects;
/**
* Vocabulary
*/
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-10-05T14:50:20.183+02:00")

@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
public class Vocabulary   {
        @JsonProperty("voc")
        private String voc = null;

    public Vocabulary voc(String voc) {
    this.voc = voc;
    return this;
    }

    /**
        * Get voc
    * @return voc
    **/
      public String getVoc() {
    return voc;
    }

    public void setVoc(String voc) {
    this.voc = voc;
    }


@Override
public boolean equals(java.lang.Object o) {
if (this == o) {
return true;
}
if (o == null || getClass() != o.getClass()) {
return false;
}
    Vocabulary vocabulary = (Vocabulary) o;
    return Objects.equals(voc, vocabulary.voc);
}

@Override
public int hashCode() {
return Objects.hash(voc);
}

@SuppressWarnings("StringBufferReplaceableByString")
@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append("class Vocabulary {\n");

sb.append("    voc: ").append(toIndentedString(voc)).append("\n");
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
