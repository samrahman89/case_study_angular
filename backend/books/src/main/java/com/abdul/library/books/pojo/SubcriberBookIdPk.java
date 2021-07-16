package com.abdul.library.books.pojo;

import java.io.Serializable;

public class SubcriberBookIdPk implements Serializable {

    private static final long serialVersionUID = 1L;

    private String subscriberName;
    
    private String bookId;
    
    public SubcriberBookIdPk() {
    }

    public SubcriberBookIdPk(String subscriberName, String bookId) {
        this.subscriberName = subscriberName;
        this.bookId = bookId;
    }
    

    public String getSubscriberName() {
        return subscriberName;
    }

    public String getBookId() {
        return bookId;
    }

    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
        result = prime * result + ((subscriberName == null) ? 0 : subscriberName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SubcriberBookIdPk other = (SubcriberBookIdPk) obj;
        if (bookId == null) {
            if (other.bookId != null)
                return false;
        } else if (!bookId.equals(other.bookId))
            return false;
        if (subscriberName == null) {
            if (other.subscriberName != null)
                return false;
        } else if (!subscriberName.equals(other.subscriberName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SubcriberBookIdPk [subscriberName=" + subscriberName + ", bookId=" + bookId + "]";
    }
    
}
