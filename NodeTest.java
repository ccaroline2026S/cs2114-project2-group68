package adventurerGo;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class NodeTest
{
    private Node<String> node;
    private Node<String> nextNode;

    @Before
    public void setUp()
    {
        node = new Node<String>("Paris");
        nextNode = new Node<String>("Rome");
    }

    @Test
    public void testConstructor()
    {
        assertEquals("Paris", node.getData());
        assertNull(node.getNext());
    }

    @Test
    public void testGetData()
    {
        assertEquals("Paris", node.getData());
    }

    @Test
    public void testSetData()
    {
        node.setData("Tokyo");
        assertEquals("Tokyo", node.getData());
    }

    @Test
    public void testGetNext()
    {
        node.setNext(nextNode);
        assertEquals(nextNode, node.getNext());
    }

    @Test
    public void testSetNext()
    {
        node.setNext(nextNode);
        assertSame(nextNode, node.getNext());
    }

    @Test
    public void testSetNextNull()
    {
        node.setNext(null);
        assertNull(node.getNext());
    }
}
