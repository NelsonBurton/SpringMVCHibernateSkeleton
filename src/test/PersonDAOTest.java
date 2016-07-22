@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class PersonDAOTest {

    @Autowired
    private PersonDAO personDAO;

    @Test
    public void testAddingPerson() {
        Person p = new Person();
        assert false;
    }

}