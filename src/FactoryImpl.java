import java.util.HashSet;
import java.util.NoSuchElementException;

public class FactoryImpl implements Factory{

    private Holder first;
    private Holder last;
    private Integer size;

    public void doClear(){
        first = new Holder(null,null,null);
        last = new Holder(first,null,null);
        first.setNextHolder(last);
        size=0;
    }
    public boolean isEmpty() {
        return size() == 0;
    }
    public int size() {
        return size;
    }


    @Override
    public void addFirst(Product product){
        Holder anan = new Holder(null,product,first);
        if(isEmpty()){
            last = anan;
        }else{
            first.setPreviousHolder(anan);
        }
        first = anan;
        size++;

    }

    @Override
    public void addLast(Product product) {
        Holder anan = new Holder(last,product,null);
        if(isEmpty()){
            first=anan;

        }else{
            last.setNextHolder(anan);

        }
        last=anan;
        size++;

    }

    @Override
    public Product removeFirst() throws NoSuchElementException {
        if(isEmpty()){

            throw new NoSuchElementException();
        }
        Holder temp = first;
        if(first==last){
            last=null;

        }else{
            first.getNextHolder().setPreviousHolder(null);
        }
        first =first.getNextHolder();
        temp.setNextHolder(null);
        size--;
        return temp.getProduct();
    }

    @Override
    public Product removeLast() throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        Holder temp = last;
        if(first==last){
            first = null;
        }else{
            last.getPreviousHolder().setNextHolder(null);
        }
        last = last.getPreviousHolder();
        temp.setPreviousHolder(null);
        size--;
        return temp.getProduct();

    }

    @Override
    public Product find(int id) throws NoSuchElementException {
        Holder anan;
        anan = first;
        Integer flag=0;
        while(anan.getProduct().getId()!=id){
            anan = anan.getNextHolder();
            flag++;
        }
        if(flag==size()){
            throw new NoSuchElementException();
        }else{
            return anan.getProduct();
        }

    }

    @Override
    public Product update(int id, Integer value) throws NoSuchElementException {
        Holder anan;
        anan = first;
        Integer flag=0;

        Integer del;
        while(anan.getProduct().getId()!=id){
            anan = anan.getNextHolder();
            flag++;
        }
        if(flag==size()){
            System.out.println("Product not found.");
            throw new NoSuchElementException();
        }else{
            del = anan.getProduct().getValue();
        }
        anan.getProduct().setValue(value);
        Product deletedAnan = new Product(anan.getProduct().getId(),del);
        return deletedAnan;
    }
    @Override
    public Product get(int index) throws IndexOutOfBoundsException {
        Holder anan;
        if(index<0 || index>size-1) {
            throw new IndexOutOfBoundsException();
        }
        anan  =first;
        for(int i=0;i<index;i++){
            anan = anan.getNextHolder();
        }
        return anan.getProduct();
    }



    @Override
    public void add(int index, Product product) throws IndexOutOfBoundsException {
        Holder anan;
        if(index<0 || index>size()) {
            throw new IndexOutOfBoundsException();
        }
        if (index==0){
            addFirst(product);
        }
        else if (index==size()){
            addLast(product);
        }else {
            anan = first;
            for (int i = 0; i < index-1; i++) {
                anan = anan.getNextHolder();
            }
            /*Holder zum;
            zum = last;
            for (int i = size - 1; i > index; i--) {
                zum = zum.getPreviousHolder();
            }*/
            Holder yenisey = new Holder(anan, product, anan.getNextHolder());
            anan.getNextHolder().setPreviousHolder(yenisey);
            anan.setNextHolder(yenisey);

            size++;
        }
    }
    @Override
    public Product removeIndex(int index) throws IndexOutOfBoundsException {
        Holder anan;
        if (index < 0 || index > size-1) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size-1) {
            return removeLast();


        }else if(index ==0){
            return removeFirst();
        }
        else {
            anan = first;
            for (int i = 0; i < index; i++) {
                anan = anan.getNextHolder();
            }
            if (anan == first) {
                first = anan.getNextHolder();
            }else if(anan==last){
                last = last.getPreviousHolder();
            } else {
                anan.getPreviousHolder().setNextHolder(anan.getNextHolder());
                anan.getNextHolder().setPreviousHolder(anan.getPreviousHolder());
            }
            size--;
            return anan.getProduct();
        }
    }

    @Override
    public Product removeProduct(int value) throws NoSuchElementException {
        Holder anan;
        anan = first;
        Integer flag=0;
        while(anan.getProduct().getValue()!=value){
            anan = anan.getNextHolder();
            flag++;
        }
        if(flag==size()){
            throw new NoSuchElementException();

        }else if (flag==0){
            return removeFirst();
        }else if (flag==size-1){
            return removeLast();
        }
        else{
            anan.getPreviousHolder().setNextHolder(anan.getNextHolder());
            anan.getNextHolder().setPreviousHolder(anan.getPreviousHolder());
            size--;
            return anan.getProduct();
        }


    }

    @Override
    public int filterDuplicates() {
        HashSet<Integer> mySet = new HashSet<>();
        Holder anan ;
        anan = first;
        Integer count=0;
        while (anan != null){
            if(anan.getProduct()!=null){
                if (mySet.contains(anan.getProduct().getValue())){
                    if (anan.getNextHolder()==null){
                        anan.getPreviousHolder().setNextHolder(null);
                        last = last.getPreviousHolder();
                        count++;
                    }else{
                        anan.getPreviousHolder().setNextHolder(anan.getNextHolder());
                        anan.getNextHolder().setPreviousHolder(anan.getPreviousHolder());
                        count++;
                    }
                }else{
                    mySet.add(anan.getProduct().getValue());
                }
            }
            anan = anan.getNextHolder();
        }
        size -=count;
        return count;
    }

    @Override
    public void reverse() {
        Holder anan;
        Holder temp;
        temp =null;
        anan=first;
        while (anan != null){
            temp = anan.getNextHolder();
            anan.setNextHolder(anan.getPreviousHolder());
            anan.setPreviousHolder(temp);
            anan = anan.getPreviousHolder();
        }
        temp=first;
        first=last;
        last=temp;
    }
    public String print(){
        Holder anan;
        anan=first;
        String fin ="";
        fin += "{";
        Integer flag = 0;
        if (anan!= null) {
            for (int i = 0; i < size; i++) {
                fin += (anan.getProduct().toString());
                flag += 1;
                anan = anan.getNextHolder();
                if (flag != size) {
                    fin += ",";
                }
                if (i == size - 1) {
                    break;
                }
            }
        }
        fin+="}";
        return fin;
    }
}