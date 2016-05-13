package service1.types;

public class Service1Call1Output {
  public String field1;

  public Service1Call1Output(String field1) {
    this.field1 = field1;
  }

  @Override
  public String toString() {
    return "field1 = " + field1;
  }
}
