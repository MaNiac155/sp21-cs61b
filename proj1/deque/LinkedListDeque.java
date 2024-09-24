package deque;

class ListNode<T>{
    T value;
    ListNode next;
    ListNode prev;
    public ListNode(){
        this.value = null;
        this.next = null;
        this.prev = null;
    }
    public ListNode(T value, ListNode next, ListNode prev){
        this.value = value;
        this.next = next;
        this.prev = prev;
    }

}

public class LinkedListDeque<T> {
    ListNode<T> head;
    int size=0;
    public LinkedListDeque() {
        head=new ListNode<T>(null,null,null);

    }

    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        ListNode<T>temp = head.next;
        if(temp==null){
            return;
        }
        for(;temp.value!=null;temp=temp.next){
            System.out.print(temp.value+" ");
        }
    }

    public void addFirst(T item){
        size++;
        ListNode<T> newNode=new ListNode<T>(item,head.next,head);
        if(size==1){
            newNode.next=head;
            head.prev=newNode;
            head.next=newNode;
        }
        else{
            head.next.prev=newNode;
            head.next=newNode;

        }
    }
    public void addLast(T item){
        size++;
        ListNode<T> newNode = new ListNode<T>(item,head,head.prev);
        if(size==1){
            newNode.prev=head;
            head.next=newNode;
            head.prev=newNode;
        }
        else{
            head.prev.next=newNode;
            head.prev=newNode;
        }
    }
    public T removeFirst(){
        if(size==0) return null;
        ListNode<T> node=head.next;
        head.next=node.next;
        node.next.prev=head;
        T value=node.value;
        node=null;
        size--;
        return value;

    }
    public T removeLast(){
        if(size==0) return null;
        ListNode<T> node=head.prev;
        head.prev=node.prev;
        node.prev.next=head;
        T value=node.value;
        node=null;
        size--;
        return value;
    }

    public T get(int index){
        ListNode<T> node=head.next;
        for(int i=0;i<index;i++){
            node=node.next;
        }
        return node.value;
    }





    public static void main(String[] args) {
        LinkedListDeque deque=new LinkedListDeque<Integer>();
        deque.printDeque();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.removeFirst();
        deque.removeLast();
        deque.printDeque();

    }


}
