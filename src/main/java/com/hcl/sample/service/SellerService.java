package com.hcl.sample.service;


import com.hcl.sample.Repository.SellerRepository;
import com.hcl.sample.model.Seller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    private Logger logger = (Logger) LoggerFactory.getLogger(SellerService.class);

    @Autowired
    SellerRepository sellerRepository ;

    public void setSellerRepository(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public Seller createSeller( Seller seller){
        return  sellerRepository.save(seller);
    }

    public  Seller updateSeller( Seller seller){
        return  sellerRepository.save(seller);
    }

    public List<Seller> getAllSellers(){
        return  (List<Seller>)sellerRepository.findAll();
    }

    public Seller getSellerById(int sellerId){
        Optional<Seller> optionalSeller = sellerRepository.findById(sellerId);
        if(optionalSeller.isPresent()) {
            return optionalSeller.get();
        }
        else {
            return null;
        }
    }

    public List<Seller> getSellerByProductId(int productId){
        return sellerRepository.findByProductProductId(productId);
    }

    public void deleteSeller( Seller seller){
        sellerRepository.delete(seller);
    }
}
