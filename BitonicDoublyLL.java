class Node{
    int data;
    Node next;
    Node prev;

    Node(int data){
        this.data=data;
        next=null;
        prev=null;
    }
}
public class BitonicDoublyLL {

    Node head;

    void addNode(int data){
        Node newNode=new Node(data);
        if(head==null){
            head=newNode;
        }
        else{
            Node temp =head;
            while (temp.next!=null) temp =temp.next;
            temp.next=newNode;
            newNode.prev=temp;
        }
    }

    void printList(){
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    Node reverse(Node head){
        Node prev=null, currNode=head;
        while(currNode!=null){
            Node next=currNode.next;
            currNode.next=prev;
            currNode.prev=next;
            prev=currNode;
            currNode=next;
        }
    return prev;
    }
    
    Node merge(Node a, Node b){
        if (a == null) return b;
        if (b == null) return a;

        if (a.data <= b.data) {
            a.next = merge(a.next, b);
            if (a.next != null) a.next.prev = a;
            a.prev = null;
            return a;
        } else {
            b.next = merge(a, b.next);
            if (b.next != null) b.next.prev = b;
            b.prev = null;
            return b;
        }
    }

    void sortBitonic(){
        if (head == null || head.next == null) return;

        // find the peak (end of increasing sequence)
        Node curr = head;
        while (curr.next != null && curr.data <= curr.next.data){
            curr = curr.next;
        }

        // if already fully increasing, nothing to do
        if (curr.next == null) return;

        // split into two lists: head..curr and curr.next..end
        Node head2 = curr.next;
        curr.next = null;
        if (head2 != null) head2.prev = null;

        // reverse the second (which is in descending order) to make it ascending
        head2 = reverse(head2);

        // merge two sorted lists
        head = merge(head, head2);
        if (head != null) head.prev = null;
    }

    public static void main(String[] args){
        BitonicDoublyLL list = new BitonicDoublyLL();
        // sample bitonic list: increasing then decreasing
        list.addNode(10);
        list.addNode(20);
        list.addNode(40);
        list.addNode(30);
        list.addNode(5);

        System.out.print("Original: ");
        list.printList();

        list.sortBitonic();

        System.out.print("Sorted:   ");
        list.printList();
    }
}
