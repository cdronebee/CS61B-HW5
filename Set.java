/* Set.java */

import list.*;

/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/
public class Set {
  /* Fill in the data fields here. */
    //ListNode head;
    protected DList alist;

    //public interface Comparable T; included in java.lang
    
  /**
   * Set ADT invariants:
   *  1)  The Set's elements must be precisely the elements of the List.
   *  2)  The List must always contain Comparable elements, and those elements 
   *      must always be sorted in ascending order.
   *  3)  No two elements in the List may be equals().
   **/

  /**
   *  Constructs an empty Set. 
   *
   *  Performance:  runs in O(1) time.
   **/
  public Set() { 
    // Your solution here.
      //super() compiled, but toString overflowed;  //Why can't i use 'this' for the code below? Compiling erros.
      alist = new DList();
      
  }

  /**
   *  cardinality() returns the number of elements in this Set.
   *
   *  Performance:  runs in O(1) time.
   **/
  public int cardinality() {
    // Replace the following line with your solution.
      return alist.length();
  }

  /**
   *  insert() inserts a Comparable element into this Set.
   *
   *  Sets are maintained in sorted order.  The ordering is specified by the
   *  compareTo() method of the java.lang.Comparable interface.
   *
   *  Performance:  runs in O(this.cardinality()) time.
   **/
  public void insert(Comparable c) {
    // Your solution here.
    
      if (alist.isEmpty()) {  //If empty, insert the first node.
	  alist.insertFront(c);
      }
      else try {
	      ListNode cursor = alist.front();
	      while (cursor.isValidNode()) {
		  if (c.compareTo(cursor.item()) < 0) {  //Insert if smaller than current element
		      cursor.insertBefore(c);
		      break;
		  }
		  else if (c.compareTo(cursor.item()) > 0) {
			  if (cursor == alist.back()) {
			      alist.insertBack(c);  //Insert if still larger than last element
			  }
			  else {cursor = cursor.next();  //Keep moving cursor until it's smaller.
			  }
		  }
		  else {
		      break;  //Break the loop if equal
		  }
	      }
	  }
      catch (InvalidNodeException e) { System.out.println("Error: " + e);} 
  }

  /**
   *  union() modifies this Set so that it contains all the elements it
   *  started with, plus all the elements of s.  The Set s is NOT modified.
   *  Make sure that duplicate elements are not created.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Your implementation should NOT copy elements of s or "this", though it
   *  will copy _references_ to the elements of s.  Your implementation will
   *  create new _nodes_ for the elements of s that are added to "this", but
   *  you should reuse the nodes that are already part of "this".
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
   **/
  public void union(Set s) {
    // Your solution here.
      if (alist.isEmpty()) {
	  alist = s.alist;
      }
      if (!s.alist.isEmpty()){
	  try {
	      ListNode scursor = s.alist.front(); //cursor in Set s
	      ListNode acursor = alist.front(); // cursor in alist, to be updated once s insert items
	      while (acursor != alist.back()) {
		  Comparable c = (Comparable)scursor.item();
		  if (c.compareTo(acursor.item()) < 0) {
		      acursor.insertBefore(scursor.item());
		      scursor = scursor.next();
		  }
		  if (c.compareTo(acursor.item()) == 0) {
		      scursor = scursor.next();
		  }
		  if (c.compareTo(acursor.item()) > 0) {
		      acursor = acursor.next();
		  }
	      }
	      while (scursor != s.alist.back().next()) {
		  Comparable c = (Comparable)scursor.item();
		  if (c.compareTo(acursor.item()) > 0) {
		      acursor.insertAfter(scursor.item());
		      acursor = acursor.next();
		  }
		  scursor = scursor.next();
	      }	      
	  }
	  catch (InvalidNodeException e) { System.out.println("Error: " + e);}   
      }
  }

  /**
   *  intersect() modifies this Set so that it contains the intersection of
   *  its own elements and the elements of s.  The Set s is NOT modified.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Do not construct any new ListNodes during the execution of intersect.
   *  Reuse the nodes of "this" that will be in the intersection.
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT CONSTRUCT ANY NEW NODES.
   *  DO NOT ATTEMPT TO COPY ELEMENTS.
   **/
  public void intersect(Set s) {
    // Your solution here.
       if (s.alist.isEmpty()) {
	  alist = null;
      }
       else {
	  try {
	      ListNode scursor = s.alist.front(); //cursor in Set s
	      ListNode acursor = alist.front(); // cursor in alist, to be updated once s insert items
	      ListNode tempcursor = acursor;
	      while (acursor != alist.back()) {
		  Comparable c = (Comparable)scursor.item();
		  if (c.compareTo(acursor.item()) < 0) {
		      scursor = scursor.next();
		  }
		  if (c.compareTo(acursor.item()) == 0) {
		      scursor = scursor.next();
		      acursor = acursor.next();
		  }
		  if (c.compareTo(acursor.item()) > 0) {
		      tempcursor = acursor.next();
		      acursor.remove();
		      acursor = tempcursor;
		  }
	      }
	      
	  }
	  catch (InvalidNodeException e) { System.out.println("Error: " + e);}   
      }
       
      
  }

  /**
   *  toString() returns a String representation of this Set.  The String must
   *  have the following format:
   *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
   *            between them.
   *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
   *            "{" or after "}"; two spaces before and after each element.
   *            Elements are printed with their own toString method, whatever
   *            that may be.  The elements must appear in sorted order, from
   *            lowest to highest according to the compareTo() method.
   *
   *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
   *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
   *            DEVIATIONS WILL LOSE POINTS.
   **/
  public String toString() {
    // Replace the following line with your solution.
      String result = alist.toString();
    result = "{" + result.substring(1, result.length()-1);
    return result + "}";
  }

  public static void main(String[] argv) {
    Set s = new Set();
    System.out.println("Empty Set() is: (Testing toString, constructor)  " + s);
    System.out.println("Length is: " + s.cardinality());
    s.insert(new Integer(3));
    //System.out.println("After first insert, 3:  " + s);
    s.insert(new Integer(4));
    //System.out.println("After inserting 3, 4: " + s);
    s.insert(new Integer(3));
    //System.out.println("After inserting 3 4 3: " + s);
    System.out.println("Set s = " + s);

    Set s2 = new Set();
    s2.insert(new Integer(4));
    //System.out.println("After first insert, 4:  " + s2);
    s2.insert(new Integer(5));
    //System.out.println("After inserting 5:  " + s2);
    s2.insert(new Integer(5));
    //System.out.println("After inserting another 5:  " + s2);
    System.out.println("Set s2 = " + s2);

    Set s3 = new Set();
    s3.insert(new Integer(5));
    s3.insert(new Integer(3));
    s3.insert(new Integer(8));
    System.out.println("Set s3 = " + s3);

    Set s4 = new Set();
    s4.insert(new Integer(5));
    s4.insert(new Integer(2));
    s4.insert(new Integer(4));
    s4.insert(new Integer(2));
    s4.insert(new Integer(7));
    s4.insert(new Integer(1));
    s4.insert(new Integer(6));
    //System.out.println("Set s4, inserting 5 2 4 2 7 1 6: " + s4);
    s4.insert(new Integer(3));
    System.out.println("Set s4 = " + s4);

    s.union(s2);
    System.out.println("After s.union(s2), s = " + s);

    s.intersect(s3);
    System.out.println("After s.intersect(s3), s = " + s);

    System.out.println("s.cardinality() = " + s.cardinality());
    // You may want to add more (ungraded) test code here.
  }
}
