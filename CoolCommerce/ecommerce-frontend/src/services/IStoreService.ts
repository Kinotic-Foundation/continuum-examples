import {IServiceProxy, Continuum} from '@kinotic/continuum-client'
import Category from '@/domain/Category'
import Product from '@/domain/Product'
import { CheckoutInfo } from '@/domain/CheckoutInfo'

/**
 * Provides functionality to access store information
 */
export interface IStoreService {

    getAllCategories(): Promise<Category[]>

    getAllProductsForCategoryId(categoryId: number): Promise<Product[]>

    getProduct(productId: number): Promise<Product>

    checkout(checkoutInfo: CheckoutInfo): Promise<string>
}

/**
 * Default implementation of IStoreService
 */
export class StoreService implements IStoreService {

    protected serviceProxy: IServiceProxy

    constructor() {
        this.serviceProxy = Continuum.serviceProxy('com.coolcompany.ecommerce-main.api.StoreService')
    }

    getAllCategories(): Promise<Category[]> {
        return this.serviceProxy.invoke('getAllCategories')
    }

    getAllProductsForCategoryId(categoryId: number): Promise<Product[]> {
        return this.serviceProxy.invoke('getAllProductsForCategoryId', [categoryId])
    }

    getProduct(productId: number): Promise<Product> {
        return this.serviceProxy.invoke('getProduct', [productId])
    }

    checkout(checkoutInfo: CheckoutInfo): Promise<string> {
        return this.serviceProxy.invoke('checkout', [checkoutInfo])
    }

}

export const STORE_SERVICE: IStoreService = new StoreService()
