/**
 *
 */
package rapticon.tk.leavemanagement.util;


public enum AdapterElement {

    LEAVE(1),
    ALL_LEAVE(2);


    private int element;

    /**
     * @param element
     */
    private AdapterElement(int element) {
        this.element = element;
    }

    /**
     * @return the element
     */
    public int getElement() {
        return element;
    }

}
