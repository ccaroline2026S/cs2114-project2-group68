package adventurerGo;

//T makes the class generic so it would only store one specific type
public class Node<T>
{
    public T data;
    private Node<T> next;

    // for every method it's o(1) becuase it only accesses and updates one field 
   public Node(T dataPortion) {
      this(dataPortion, null);
   }
   
   public Node(T dataPortion, Node<T> nextNode) {
       data = dataPortion;
       next = nextNode;
   }
   
   public T getData() {
       return data;
   }
   
   public void setData(T newData) {
       data = newData;
   }
   
   public Node<T> getNext() {
       return next;
   }
   
   public void setNext(Node<T> newNext) {
       next = newNext;
   }
}
