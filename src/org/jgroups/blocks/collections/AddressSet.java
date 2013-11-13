package org.jgroups.blocks.collections;

import java.io.DataInput;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jgroups.Address;
import org.jgroups.util.Util;

/**
 * Maintain ordering!
 */
public class AddressSet<A extends Address> implements Iterable<A>, Cloneable, Collection<A> {

    private AddressSet(Address[] retval) {
        // TODO Auto-generated constructor stub
    }

    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    public Iterator<A> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    public static final <T extends Address> AddressSet<T> newEmptySet() {
        // TODO Auto-generated method stub
        return null;
    }

    public void clear() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public AddressSet<A> clone() {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean contains(A suspect) {
        // TODO Auto-generated method stub
        return false;
    }

    public static AddressSet fromDataInput(DataInput in) throws Exception {
        short length=in.readShort();
        if(length < 0) return newEmptySet();
        Address[] retval=new Address[length];
        for(int i=0; i < length; i++) {
            Address addr=Util.readAddress(in);
            retval[i]=addr;
        }
        return new AddressSet(retval);
    }

    public static AddressSet unmodifiable(AddressSet members) {
        // TODO Auto-generated method stub
        return null;
    }

    public int positionOf(Address member) {
        if(member == null)
            return -1;
        for(int i=0; i < members.length; i++)
            if(member.equals(members[i]))
                return i;
        return -1;
    }

    /**
     * Get an Address by it's ordinal (or return null if it's out of range)
     * @param index
     * @return
     */
    public A get(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    public static <T extends Address> AddressSet<T> singleton(T address) {
        if (address==null) {
            return immutableEmptySet();
        }
        // TODO Auto-generated method stub
        return null;
    }

    public void removeAll(AddressSet<A> mbrs) {
        // TODO Auto-generated method stub
        
    }

    public A getFirst() {
        // TODO Auto-generated method stub
        return null;
    }

    public void sort() {
        // TODO Auto-generated method stub
        
    }

    public boolean remove(A old_member) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean retainAll(AddressSet<A> v) {
        // TODO Auto-generated method stub
        return false;
    }

    public static boolean equals(final AddressSet<Address> m, final AddressSet<Address> n) {
        if (m==n)
            return true;
        if (m==null)
            return false;
        return m.equals(n);
    }

    public boolean containsAll(AddressSet<Address> members) {
        // TODO Auto-generated method stub
        return false;
    }

    public static <T extends Address> AddressSet<T> newEmptySet(int sizeHint) {
        // TODO Auto-generated method stub
        return null;
    }

    public static <T extends Address> AddressSet<T> fromSet(Set<T> keySet) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean contains(Object o) {
        // TODO Auto-generated method stub
        return false;
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
    public boolean add(A e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean remove(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends A> c) {
        // TODO Auto-generated method stub
        return false;
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

    public static <T extends Address> AddressSet<T> immutableEmptySet() {
        // TODO Auto-generated method stub
        return null;
    }

}
