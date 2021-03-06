package com.project.wei.tastyrecipes.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ITEM的对应可序化队列属性
 *  */
  public class ChannelItem implements Parcelable{

//	private static final long serialVersionUID = -6465237897027410019L;

	public int id;

	public String name;

	public int orderId;

	public int selected;

	protected ChannelItem(Parcel in) {
		id = in.readInt();
		name = in.readString();
		orderId = in.readInt();
		selected = in.readInt();
	}

	public static final Creator<ChannelItem> CREATOR = new Creator<ChannelItem>() {
		@Override
		public ChannelItem createFromParcel(Parcel in) {
			return new ChannelItem(in);
		}

		@Override
		public ChannelItem[] newArray(int size) {
			return new ChannelItem[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(name);
		dest.writeInt(orderId);
		dest.writeInt(selected);
	}

	public ChannelItem() {
	}

	public ChannelItem(int id, String name, int orderId,int selected) {
		this.id = Integer.valueOf(id);
		this.name = name;
		this.orderId = Integer.valueOf(orderId);
		this.selected = Integer.valueOf(selected);
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public int getOrderId() {
		return this.orderId;
	}

	public Integer getSelected() {
		return this.selected;
	}

	public void setId(int paramInt) {
		this.id = Integer.valueOf(paramInt);
	}

	public void setName(String paramString) {
		this.name = paramString;
	}

	public void setOrderId(int paramInt) {
		this.orderId = Integer.valueOf(paramInt);
	}

	public void setSelected(Integer paramInteger) {
		this.selected = paramInteger;
	}

	public String toString() {
		return "ChannelItem [id=" + this.id + ", name=" + this.name
				+ ", selected=" + this.selected + "]";
	}
}