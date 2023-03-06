import { IServiceRegistry, IServiceProxy } from '@kinotic-foundation/continuum-js'
import { inject, injectable, container } from 'inversify-props'
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

    checkout(checkoutInfo: CheckoutInfo): Promise<String>
}

/**
 * Default implementation of IStoreService
 */
@injectable()
export class StoreService implements IStoreService {

    protected serviceProxy: IServiceProxy

    constructor(@inject() serviceRegistry: IServiceRegistry) {
        this.serviceProxy = serviceRegistry.serviceProxy('com.coolcompany.ecommerce-main.api.StoreService')
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

container.addSingleton<IStoreService>(StoreService)
