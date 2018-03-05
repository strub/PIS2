package data_treatment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import tc.TC;

public class ConcurrentQueue<E> implements BlockingQueue<E> {
	final int capacity = 20 ;
	private final ArrayList<E> elements ;
	private int head ;
	private int fillingSize ;
	private final Lock lock ; 
	private final Condition notEmpty, notFull ;
	private Integer Score = 0 ;
	
	public ConcurrentQueue(){
		this.elements = new ArrayList<E>(capacity) ;
		for(int i = 0 ; i <capacity ; i++) elements.add(null) ;
		this.head = this.fillingSize = 0 ;
		this.lock = new ReentrantLock() ;
		this.notEmpty = lock.newCondition() ;
		this.notFull = lock.newCondition() ;
	}
	
	public void put (E s) throws InterruptedException {
		if(TC.motsDeChaine((java.lang.String) s).length > 1){
			System.out.println("Error"); return ;
		}
		lock.lock();
		try{
			while(fillingSize == capacity) notFull.awaitUninterruptibly();
			boolean wasEmpty = (fillingSize == 0) ;
			elements.set((head+fillingSize)%capacity, s) ;
			fillingSize ++ ;
			
			if(wasEmpty){
				notEmpty.signalAll(); 
			}
		} finally {
			lock.unlock();
		}
	}
	
	public E take() throws InterruptedException {

		lock.lock();
		try{
			while(fillingSize == 0) notEmpty.awaitUninterruptibly();
			boolean wasFull = (fillingSize == capacity) ;
			E s = elements.get(head) ;
			elements.set(head, null) ;
			head = (head+1)%capacity ;
			fillingSize -- ;
			
			if(wasFull){
				notFull.signalAll(); 
			}
			return s ;
		} finally {
			lock.unlock();
		}
	}
	
	public String toString(){
		return "La pile contient : "  + elements.toString() ;
		
	}

	@Override
	public E element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int drainTo(Collection<? super E> c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int drainTo(Collection<? super E> c, int maxElements) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean offer(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E poll(long timeout, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int remainingCapacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
