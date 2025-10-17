package com.desafios_backend.criptography;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Collection;

@Service
public class CardService {

    @Value("${cripto.publicKey}")
    private PublicKey publicKey;

    @Value("${cripto.privateKey}")
    private PrivateKey privateKey;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CardRepository cardRepository;

    public CardModel createCard(CardDto cardDto) throws Exception {
        CardModel cardModel = this.modelMapper.map(cardDto, CardModel.class);
        cardModel.setCreditCardToken(RSAUtils.encrypt(cardModel.getCreditCardToken(), this.publicKey));
        cardModel.setUserDocument(RSAUtils.encrypt(cardModel.getUserDocument(), this.publicKey));

        return this.cardRepository.save(cardModel);
    }

    public Collection<CardModel> getAllCards() throws Exception{
        Collection<CardModel> cardModelCollection = this.cardRepository.findAll();
        return this.decryptMany(cardModelCollection);
    }

    private Collection<CardModel> decryptMany(Collection<CardModel> cards) throws Exception {

        return cards.stream().map(cardModel -> {
            try {
                cardModel.setCreditCardToken(RSAUtils.decrypt(cardModel.getCreditCardToken(), this.privateKey));
                cardModel.setUserDocument(RSAUtils.decrypt(cardModel.getUserDocument(), this.privateKey));
                return cardModel;
            } catch (Exception e) {
                throw new RuntimeException("Erro ao descriptografar cartão", e);
            }
        }).toList();
    }
}
