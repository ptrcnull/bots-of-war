package ml.bjorn.bow.botsofwar;

public class Tile {
	private static final String STATIC_IMG = "https://botsofwar.bjorn.ml/static/img";
  public static String[] types = { "dirt", "grass", "water", "stone", "base", "mine" };
  private Integer type;
  private Entity entity;
  private Boolean hasCoordinates;

  public Tile(Integer type) {
    this.type = type;
    this.hasCoordinates = false;
  }

  public void clearCoordinates() {
    hasCoordinates = false;
  }

  public void markCoordinates() {
    hasCoordinates = true;
  }

  public Entity getEntity() {
    return this.entity;
  }

  public void setEntity(Entity entity) {
    this.entity = entity;
  }

  public String getType() {
    return types[type];
  }

  public String getHTMLImageTag(Boolean hasPath) {
    String str = "";
    if (hasCoordinates) str += new Image("/point.png");
    if (entity != null) str += new Image("/entities/" + entity.getName().toLowerCase() + ".png");
    return String.format(
      "<div class=\"img\" style=\"background-image: url('%s/tiles/%s.png')\">%s</div>",
      STATIC_IMG, hasPath ? "path" : getType(), str
    );
  }

  public String getHTMLImageTag() {
    return getHTMLImageTag(false);
  }

  public String getConsoleValue() {
    if (hasCoordinates) return "7";
    if (entity != null) return "8";
    return Integer.toString(type);
  }

  public Boolean isObstacle() {
    return !(type == 0 || type == 1);
  }

  class Image {
    String url;
    public Image(String url) { this.url = url; }
    public String toString() { return String.format("<img src=\"%s%s\">", STATIC_IMG, url); }
  }
}