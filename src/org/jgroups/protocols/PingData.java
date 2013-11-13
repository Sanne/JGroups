
package org.jgroups.protocols;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.Collection;

import org.jgroups.Address;
import org.jgroups.Global;
import org.jgroups.PhysicalAddress;
import org.jgroups.View;
import org.jgroups.ViewId;
import org.jgroups.blocks.collections.AddressSet;
import org.jgroups.util.SizeStreamable;
import org.jgroups.util.Util;

/**
 * Encapsulates information about a cluster node, e.g. local address, coordinator's address, logical name and
 * physical address(es)
 * @author Bela Ban
 */
public class PingData implements SizeStreamable {
    protected Address sender=null;  // the sender of this PingData
    protected View    view=null;    // only sent with merge-triggered discovery response (if ViewIds differ)
    protected ViewId  view_id=null; // only sent with GMS-triggered discovery response
    protected boolean is_server=false;
    protected String  logical_name=null;
    protected AddressSet<PhysicalAddress> physical_addrs=null;


    public PingData() {
    }

    public PingData(Address sender, View view, boolean is_server) {
        this.sender=sender;
        this.view=view;
        this.is_server=is_server;
    }


    public PingData(Address sender, View view, boolean is_server,
                    String logical_name, AddressSet<PhysicalAddress> physical_addrs) {
        this(sender, view, is_server);
        this.logical_name=logical_name;
        if(physical_addrs != null)
            this.physical_addrs=physical_addrs.clone();
    }


    public PingData(Address sender, View view, ViewId view_id, boolean is_server,
                    String logical_name, AddressSet<PhysicalAddress> physical_addrs) {
        this(sender, view, is_server, logical_name, physical_addrs);
        this.view_id=view_id;
    }


    public boolean isCoord() {
        Address coord_addr=getCoordAddress();
        return is_server && sender != null && coord_addr != null && sender.equals(coord_addr);
    }
    
    public boolean hasCoord(){
        Address coord_addr=getCoordAddress();
        return is_server && sender != null && coord_addr != null;
    }

    public Address getAddress() {
        return sender;
    }

    public Address getCoordAddress() {
        if(view_id != null)
            return view_id.getCreator();
        return view != null? view.getViewId().getCreator() : null;
    }

    public Collection<Address> getMembers() {
        return view != null? view.getMembers() : null;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view=view;
    }

    public ViewId getViewId() {return view_id;}

    public void setViewId(ViewId view_id) {this.view_id=view_id;}

    public boolean isServer() {
        return is_server;
    }

    public String getLogicalName() {
        return logical_name;
    }

    public AddressSet<PhysicalAddress> getPhysicalAddrs() {
        return physical_addrs;
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof PingData))
            return false;
        PingData other=(PingData)obj;
        return sender != null && sender.equals(other.sender);
    }

    public int hashCode() {
        int retval=0;
        if(sender != null)
            retval+=sender.hashCode();
        if(retval == 0)
            retval=super.hashCode();
        return retval;
    }

    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append(sender);
        sb.append(", " + printViewId());
        sb.append(", is_server=").append(is_server).append(", is_coord=" + isCoord());
        if(logical_name != null)
            sb.append(", logical_name=").append(logical_name);
        if(physical_addrs != null && !physical_addrs.isEmpty())
            sb.append(", physical_addrs=").append(Util.printListWithDelimiter(physical_addrs, ", "));
        return sb.toString();
    }

    public String printViewId() {
        StringBuilder sb=new StringBuilder();
        sb.append("view_id=");
        if(view_id != null)
            sb.append(view_id);
        else {
            if(view != null) {
                sb.append(view.getViewId()).append(" (");
                if(view.size() > 10)
                    sb.append(view.size() + " mbrs");
                else
                    sb.append(view);
                sb.append(")");
            }
        }
        return sb.toString();
    }

    public void writeTo(DataOutput outstream) throws Exception {
        Util.writeAddress(sender, outstream);
        Util.writeView(view, outstream);
        Util.writeViewId(view_id, outstream);
        outstream.writeBoolean(is_server);
        Util.writeString(logical_name, outstream);
        Util.writeAddresses(physical_addrs, outstream);
    }

    @SuppressWarnings("unchecked")
    public void readFrom(DataInput instream) throws Exception {
        sender=Util.readAddress(instream);
        view=Util.readView(instream);
        view_id=Util.readViewId(instream);
        is_server=instream.readBoolean();
        logical_name=Util.readString(instream);
        physical_addrs=Util.readPhysicalAddresses(instream);
    }

    public int size() {
        int retval=Global.BYTE_SIZE; // for is_server
        retval+=Util.size(sender);
        retval+=Util.size(view);
        retval+=Util.size(view_id);
        retval+=Global.BYTE_SIZE;     // presence byte for logical_name
        if(logical_name != null)
            retval+=logical_name.length() +2;
        retval+=Util.size(physical_addrs);

        return retval;
    }
}
