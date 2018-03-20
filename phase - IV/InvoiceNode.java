
public class InvoiceNode<T> {

	private Invoice Item;
	private InvoiceNode<T> next;
	
	public InvoiceNode(Invoice Item) {
		this.Item=Item;
		this.next =null;
	}
	
	public void setNext(InvoiceNode<T> nextNode) {
		this.next =nextNode;
	}
	
	
	public Invoice getItem() {
		return Item;
	}


	public InvoiceNode<T> getNext() {
		return next;
	}
	
}	
