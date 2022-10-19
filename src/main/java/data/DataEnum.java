package data;

/**
 * DataEnum - class for unification of used data.
 *
 * @author Aleksandr Liadov
 */
public enum DataEnum {
  IANA_LINK("iana.link"),
  IANA_TITLE("iana.title"),

  GOOGLE_LINK("google.link"),
  GOOGLE_SEARCH_TEXT("google.searchText"),

  USER_EMAIL("epamApp.userEmail"),
  USER_NAME("epamApp.userName"),
  USER_PASSWORD("epamApp.userPassword"),
  TARGET_PAGE("epamApp.targetPage");

  private final String value;

  DataEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
