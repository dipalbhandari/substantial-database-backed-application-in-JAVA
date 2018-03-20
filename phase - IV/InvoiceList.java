import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;



public class InvoiceList implements Iterable<Invoice> {
	private InvoiceNode<Invoice> start;
	private int size;
	private Comparator<Invoice> comp;

	public InvoiceList() {
		this.start=null;
		this.size=0;
		this.comp=getcomp();
	}

	public Comparator<Invoice> getcomp(){
		return comp;
	}

	public void add(Invoice Item) {
		InvoiceNode<Invoice> newInvoiceNode = new InvoiceNode<Invoice>(Item);
		if(start==null)
		{ 
			this.start=newInvoiceNode;
			size++;

		}

		else if(size==1)
		{  InvoiceNode<Invoice> node = start;
		size++;
		comp = new TotalComparator();
		if(comp.compare(Item,node.getItem())>0){
			newInvoiceNode.setNext(node);
			start = newInvoiceNode;		
		}
		else {
			start.setNext(newInvoiceNode);

		}
		}
		else 
		{	
			InvoiceNode <Invoice> node = start;
			while(node.getNext()!=null) {

				if(comp.compare(node.getNext().getItem(),Item)<0) {		
					//System.out.println(comp.compare(node.getNext().getItem(),Item));
					newInvoiceNode.setNext(node.getNext());
					node.setNext(newInvoiceNode);
					break;

				}
				else if( node.getNext().getNext()==null) {
					node.getNext().setNext(newInvoiceNode);

					break;
				}else {
					node = node.getNext();
				}
			}

		}

	}
	@Override
	public Iterator<Invoice> iterator() {
		return new IteratorInvoice();
	}
	public void print() {
		InvoiceNode<Invoice> temp = start;
		if(start!=null) {
			while(temp!=null) {
				System.out.println(temp.getItem());
				temp = temp.getNext();
			}
		}
		else
			System.out.println("Empty List!!!!!!!!!!!");
	}



	public 
	class IteratorInvoice implements Iterator<Invoice>{
		int index = 0;
		private InvoiceNode<Invoice> next;
		IteratorInvoice(){
			next = start;
		}
		@Override
		public boolean hasNext() {
			return (next!=null);
		}
		@Override
		public Invoice next() {
			if(next==null) 
				throw new NoSuchElementException();
			InvoiceNode<Invoice> res =next;
			next=next.getNext();
			return res.getItem();
		}
		@Override 	
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}


